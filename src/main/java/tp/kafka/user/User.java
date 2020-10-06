package tp.kafka.user;

import java.util.UUID;
import lombok.Data;

@Data
public class User {

    UUID id;
    String screenname;
    String name;
}