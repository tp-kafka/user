package tp.kafka.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@SpringBootTest
@EmbeddedKafka(topics = "chat", bootstrapServersProperty = "spring.kafka.properties.bootstrap.servers")
class KafkaServiceTests {

    @Autowired
    KafkaService kafkaService;

    @Autowired
    EmbeddedKafkaBroker embeddedKafka;

    Consumer<Object, Object> consumer;

    @BeforeEach
    public void setupConsumer(){
        var consumerProps = KafkaTestUtils.consumerProps("KafkaAdapterTests", "true", this.embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        var cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        this.consumer = cf.createConsumer();
        this.embeddedKafka.consumeFromAnEmbeddedTopic(consumer, "chat");
    }

    @AfterEach
    public void closeConsumer(){
        this.consumer.close();
    }

    @Test
    @Disabled
	void succesfulMessage() throws Exception {
       // kafkaAdapter.sendChatMessage(new ChatMessage("userId", "message"));
        
        ConsumerRecords<Object, Object> messages = KafkaTestUtils.getRecords(consumer, 5000l);
        assertThat(messages.count()).isGreaterThanOrEqualTo(1);
	}

}
