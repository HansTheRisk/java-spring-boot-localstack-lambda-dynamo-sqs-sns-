package main.repository.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@DynamoDBDocument
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    public static final Authority USER_AUTHORITY = new Authority("USER");
    public static final Authority ADMIN_AUTHORITY = new Authority("ADMIN");

    @DynamoDBAttribute
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
