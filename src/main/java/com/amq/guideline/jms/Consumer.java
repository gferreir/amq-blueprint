package com.amq.guideline.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.amq.guideline.model.Customer;

@Component
public class Consumer {
  
  private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

  @Autowired
  private JmsTemplate jmsTemplate;

  // Consuming all messages from default queue (testQueue)
  @JmsListener(destination = "${amqphub.default.queue}", containerFactory = "myFactory")
  public void receiveMessage(Customer customer){
    logger.info("Consuming message");
    logger.info(customer.toString());
  }

  // Receive the last message from a custom queue
  public Customer receiveLastMessage(String queue){
    Customer customer = (Customer)jmsTemplate.receiveAndConvert(queue);
    logger.info("Consuming message");
    logger.info(customer.toString());

    return customer;
  }

}
