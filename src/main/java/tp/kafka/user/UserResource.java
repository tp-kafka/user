package tp.kafka.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ChatResource
 */
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserResource {

    private KafkaService kafka;

    @PostMapping
    ResponseEntity<User> newUser(@RequestBody UserRequest userCreationRequest) throws URISyntaxException {
        var id = UUID.randomUUID();
        var user = new User(id, userCreationRequest.getName(), userCreationRequest.getScreenname());
        kafka.saveUser(user);
        return ResponseEntity
            .created(new URI("/user/" + id))
            .body(user);
    }

    @PutMapping("{id}")
    ResponseEntity<User> modifyUser(@PathVariable("id") UUID id, @RequestBody UserRequest userModificationRequest) {
        var user = new User(id, userModificationRequest.getName(), userModificationRequest.getScreenname());
        kafka.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        kafka.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}