package main.changelogs;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.github.dynamobee.changeset.ChangeLog;
import com.github.dynamobee.changeset.ChangeSet;
import main.repository.user.User;
import main.repository.user.Authority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", author = "dynamobee", id = "user")
    public void users(AmazonDynamoDB amazonDynamoDB) throws InterruptedException {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest tableRequest = mapper
                .generateCreateTableRequest(User.class);

        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));

        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    @ChangeSet(order = "002", author = "dynamobee", id = "admin_populate")
    public void adminPopulate(AmazonDynamoDB amazonDynamoDB) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User("admin", encoder.encode("admin"), Set.of(Authority.ADMIN_AUTHORITY));
        mapper.save(user);
    }

    @ChangeSet(order = "003", author = "dynamobee", id = "user_populate")
    public void userPopulate(AmazonDynamoDB amazonDynamoDB) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User("user", encoder.encode("user"), Set.of(Authority.USER_AUTHORITY));
        mapper.save(user);
    }

}
