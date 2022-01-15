package de.othr.sw.paymentServiceProvider.util;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


import java.util.Queue;

public class ServiceMessagingConfiguration {
    @Configuration
    @EnableJms
    public class MessagingConfiguration {
        @Value("${labresources.active-mq-broker-url}")
        private String brokerUrl;
        @Bean
        public Queue queue() {
            return (Queue) new ActiveMQQueue("TestQueue1");
        }
        @Bean
        public ActiveMQConnectionFactory connectionFatory() {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
            factory.setBrokerURL(brokerUrl);
            return factory;
        }
        @Bean
        public MessageConverter jacksonJmsMessageConverter() {
            MappingJackson2MessageConverter converter;
            converter = new MappingJackson2MessageConverter();
            converter.setTargetType(MessageType.TEXT);
            converter.setTypeIdPropertyName("_type");
            return (MessageConverter) converter;
        }
        @Bean
        public JmsTemplate jmsTemplate() {
            JmsTemplate template = new JmsTemplate(connectionFatory());
            template.setMessageConverter(jacksonJmsMessageConverter());
            return template;
        }
    }
}
