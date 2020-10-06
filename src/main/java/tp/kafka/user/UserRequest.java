package tp.kafka.user;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserRequest {
    @Length(min = 3, max=20)
    String screenname;
    
    @Length(min = 3, max=120)
    String name;
}