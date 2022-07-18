package com.amq.guideline.api;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amq.guideline.jms.Producer;
import com.amq.guideline.model.Customer;

@Component
public class CustomerCreator {

  @Autowired
  private Producer producer;

  Random random = new Random();

  List<String> names = Arrays.asList("Joao", "Jose", "Maria", "Jorge");
  List<String> countries = Arrays.asList("Brasil", "EUA", "Argentina", "Japao");
  List<String> hobbies = Arrays.asList("Video Game", "Bike", "Caminhada", "Carros");
  int minAge = 18;
  int maxAge = 80;
  
  public void createMany(int n){

    for(int i = 1; i <= n; i++){
      String rNames = names.get(random.nextInt(names.size()));
      String rCountries = countries.get(random.nextInt(countries.size()));
      String rHobbies = hobbies.get(random.nextInt(hobbies.size()));
      int rAges = random.nextInt(maxAge - minAge) + minAge;

      Customer customer = new Customer(rNames, rAges, rCountries, rHobbies);

      producer.sendMessage(customer);
    }
  }
}