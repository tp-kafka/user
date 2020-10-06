package tp.kafka.user;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ChatResource
 */
@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserResource {

    private KafkaService kafka;

    User newUser(@RequestBody User user) {
        Resource<User> resource = assembler.toResource(repository.save(newEmployee));
        return ResponseEntity
            .created(new URI(resource.getId().expand().getHref()))
            .body(resource);
    }

}