package com.amq.guideline.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amq.guideline.api.CustomerCreator;
import com.amq.guideline.jms.Consumer;
import com.amq.guideline.jms.Producer;
import com.amq.guideline.model.Customer;

@RestController
@ResponseStatus(HttpStatus.NOT_FOUND)
@RequestMapping("/transaction")
public class Controller {

  @Autowired
  private Producer producer;

  @Autowired
  private CustomerCreator customerCreator;

  @Autowired
  private Consumer consumer;

  // Send a message to default Queue (testQueue)
  @PostMapping("/send")
  @ResponseStatus(HttpStatus.CREATED)
  public void send(@RequestBody Customer customer){

    producer.sendMessage(customer);
  }

  // Send to a custom queue
  @PostMapping("/send/{queue}")
  @ResponseStatus(HttpStatus.CREATED)
  public void send(@PathVariable("queue") String queue, @RequestBody Customer customer){

    producer.sendMessage(queue, customer);
  }

  // Send 'n' message to the default queue (testQueue)
  @PostMapping("/send/many/{n}")
  @ResponseStatus(HttpStatus.CREATED)
  public void sendMany(@PathVariable("n") int n){

    customerCreator.createMany(n);
  }

  // Receive the last message from a custom queue
  @GetMapping("/receive/{queue}")
  @ResponseStatus(HttpStatus.OK)
  public Customer receiveMessage(@PathVariable("queue") String queue){

    return consumer.receiveLastMessage(queue);
  }
  
}
