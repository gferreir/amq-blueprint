package com.amq.guideline.jms;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@Configuration
@EnableJms
public class JmsConfig {
  
  @Value("${amqphub.amqp10jms.remoteUrl}")
  private String brokerUrl;

  @Value("${amqphub.amqp10jms.username}")
  private String user;

  @Value("${amqphub.amqp10jms.password}")
  private String password;

  @Value("${amqphub.default.queue}")
  private String destination;

  @Bean
  public ConnectionFactory connectionFactory() {
      if ( "".equals(user) ) {
          return new JmsConnectionFactory(brokerUrl);
      }
      return new JmsConnectionFactory(user, password, brokerUrl);
  }

  @Bean
  public JmsListenerContainerFactory<?> myFactory(
      ConnectionFactory connectionFactory,
      DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  @Bean
  public Destination orderDestination() {
    return new ActiveMQQueue(destination);
  }
  
  // Serialize message content to json using TextMessage
  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }

  @Bean
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
    template.setMessageConverter(jacksonJmsMessageConverter());
    template.setDefaultDestination(orderDestination());
    template.setDeliveryPersistent(false);
		return template;
	}

}