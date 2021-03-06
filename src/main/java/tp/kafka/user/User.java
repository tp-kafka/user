package tp.kafka.user;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User  {

    UUID id;
    String screenname;
    String name;
}