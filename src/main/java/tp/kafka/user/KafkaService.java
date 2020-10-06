package tp.kafka.user;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class KafkaService {
    private KafkaTemplate<String, User> kafka;

    public void saveUser(User user) {
        kafka.send("input", user.getId().toString(), user)
            .addCallback(this::logSuccess, this::logFail);
    }
    
    public void deleteUser(UUID id) {
        kafka.send("input", id.toString(), null)
            .addCallback(this::logSuccess, this::logFail);
    }

    void logSuccess(SendResult<String,User> success) {
        var key = success.getProducerRecord().key();
        var user = success.getProducerRecord().value();
        var topic = success.getRecordMetadata().topic();
        var partition = success.getRecordMetadata().partition();
        var offset = success.getRecordMetadata().offset();

        KafkaService.log.info("'{}' was sent to topic '{}', partition '{}', offset '{}' for key '{}'",
            user, topic, partition, offset, key
        );
    }

    void logFail(Throwable cause) {
        throw new RuntimeException(cause);
    }

}