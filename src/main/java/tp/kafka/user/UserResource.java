package tp.kafka.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * ChatResource
 */
@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserResource {

    private KafkaService kafka;

    


}