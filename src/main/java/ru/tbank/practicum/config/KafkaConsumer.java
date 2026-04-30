//package ru.tbank.practicum.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import ru.tbank.practicum.dto.BatteryEvent;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//public class KafkaConsumer {
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                bootstrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
////
////    @Bean
////    public ConsumerFactory<String, BatteryEvent> batteryEventConsumerFactory() {
////        Map<String, Object> props = new HashMap<>();
////        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
////        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
////        props.put(JsonDeserializer.TRUSTED_PACKAGES, "ru.tbank.practicum.dto");
////        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, BatteryEvent.class);
////
////        return new DefaultKafkaConsumerFactory<>(props,
////                new StringDeserializer(),
////                new JsonDeserializer<>(BatteryEvent.class, false));
////    }
////
////    @Bean
////    public ConcurrentKafkaListenerContainerFactory<String, BatteryEvent>
////    batteryEventKafkaListenerContainerFactory() {
////        ConcurrentKafkaListenerContainerFactory<String, BatteryEvent> factory =
////                new ConcurrentKafkaListenerContainerFactory<>();
////        factory.setConsumerFactory(batteryEventConsumerFactory());
////        return factory;
////    }
//}
