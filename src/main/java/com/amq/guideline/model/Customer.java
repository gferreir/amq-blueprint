package com.amq.guideline.model;

public class Customer {
  
  private String name;
  private int age;
  private String country;
  private String hobby;

  public Customer(){    
  }

  public Customer(String name, Integer age, String country, String hobby){
    this.name = name;
    this.age = age;
    this.country = country;
    this.hobby = hobby;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public void setAge(Integer age){
    this.age = age;
  }

  public int getAge(){
    return age;
  }

  public void setCountry(String country){
    this.country = country;
  }

  public String getCountry(){
    return country;
  }

  public void setHobby(String hobby){
    this.hobby = hobby;
  }

  public String getHobby(){
    return hobby;
  }

  @Override
  public String toString(){
    return String.format("Customer{name=%s, age=%d, country=%s, hobby=%s}", 
      getName(), getAge(), getCountry(), getHobby());
  }

}
