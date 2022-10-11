package com.amq.guideline.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.amq.guideline.model.Customer;

@Component
public class Producer {

  private static final Logger logger = LoggerFactory.getLogger(Producer.class);

  @Autowired
  private JmsTemplate jmsTemplate;

  // Send to default queue (testQueue)
  public void sendMessage(Customer customer) throws JmsException{
    logger.info("Sending request");

    jmsTemplate.convertAndSend(customer);
  }

  // Send to a custom queue
  public void sendMessage(String destination, Customer customer)throws JmsException {
    logger.info("Sending request");
  
    jmsTemplate.convertAndSend(destination, customer);
  }
}
