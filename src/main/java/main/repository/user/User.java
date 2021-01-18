package main.repository.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.dynamo.mapper.user.authority.AuthoritySetConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType.*;

@DynamoDBTable(tableName = "user")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @DynamoDBHashKey
    private String username;

    @DynamoDBAttribute
    private String password;

    @DynamoDBTyped(BOOL)
    private boolean accountNonExpired = true;

    @DynamoDBTyped(BOOL)
    private boolean accountNonLocked = true;

    @DynamoDBTyped(BOOL)
    private boolean credentialsNonExpired = true;

    @DynamoDBTyped(BOOL)
    private boolean enabled = true;

    @DynamoDBTypeConverted(converter = AuthoritySetConverter.class)
    private Set<Authority> authorities;

    public User(String username, String password, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
