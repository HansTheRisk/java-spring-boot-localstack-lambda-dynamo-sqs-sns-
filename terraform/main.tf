provider "aws" {
  region                      = "us-east-1"
  access_key                  = "anaccesskey"
  secret_key                  = "asecretkey"
  s3_force_path_style         = true
  skip_credentials_validation = true
  skip_metadata_api_check     = true
  skip_requesting_account_id  = true

  endpoints {
    ec2        = "http://localhost:4566"
    s3         = "http://localhost:4566"
    lambda     = "http://localhost:4566"
    iam        = "http://localhost:4566"
    apigateway = "http://localhost:4566"
    sqs        = "http://localhost:4566"
    sns        = "http://localhost:4566"
  }
}

resource "aws_sns_topic" "lambda_exec_request_topic" {
  name = "lambda_exec_request_topic"
}

resource "aws_sns_topic_policy" "lambda_exec_request_topic_policy" {
  arn = aws_sns_topic.lambda_exec_request_topic.arn

  policy = data.aws_iam_policy_document.lambda_exec_request_topic_policy_document.json
}

data "aws_iam_policy_document" "lambda_exec_request_topic_policy_document" {
  policy_id = "lambda_exec_request_topic_policy_document"

  statement {
    actions = [
      "SNS:Subscribe",
      "SNS:Receive",
      "SNS:Publish",
      "SNS:DeleteTopic",
      "SNS:AddPermission"
    ]

    condition {
      test     = "StringEquals"
      variable = "AWS:SourceOwner"

      values = [
        var.account_id,
      ]
    }

    effect = "Allow"

    principals {
      type        = "AWS"
      identifiers = ["*"]
    }

    resources = [
      aws_sns_topic.lambda_exec_request_topic.arn,
    ]

    sid = "lambda_exec_request_topic_policy_statement"
  }
}

resource "aws_sqs_queue" "lambda_exec_request_queue" {
  name = "lambda_exec_request_queue"
}

resource "aws_sqs_queue_policy" "lambda_exec_request_queue_policy" {
  queue_url = aws_sqs_queue.lambda_exec_request_queue.id

  policy = <<POLICY
	{
		"Version": "2021-01-16",
		"Id":"lambda_exec_request_queue_policy",
		"Statement": [
			{
				"Sid":"First",
				"Effect": "Allow",
				"Principal": "*",
				"Action": "sqs:SendMessage",
				"Resource": "${aws_sqs_queue.lambda_exec_request_queue.arn}",
				"Condition": {
					"ArnEquals": {
						"aws:SourceArn": "${aws_sns_topic.lambda_exec_request_topic.arn}"
					}
				}
			}
		]
	}
	POLICY
}