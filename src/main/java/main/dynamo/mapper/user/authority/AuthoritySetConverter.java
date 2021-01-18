package main.dynamo.mapper.user.authority;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import main.repository.user.Authority;

import java.util.Set;

/**
 * A custom dynamodb converter for the {@link Authority} set of objects.
 */
public class AuthoritySetConverter implements DynamoDBTypeConverter<String, Set<Authority>> {

    @SneakyThrows
    @Override
    public String convert(Set<Authority> object) {
        return new ObjectMapper().writeValueAsString(object);
    }

    @SneakyThrows
    @Override
    public Set<Authority> unconvert(String object) {
        return new ObjectMapper().readValue(object, new TypeReference<Set<Authority>>() {});
    }
}
