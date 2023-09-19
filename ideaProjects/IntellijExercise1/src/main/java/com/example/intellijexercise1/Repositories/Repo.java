package com.example.intellijexercise1.Repositories;

import com.example.intellijexercise1.Entities.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Repo {

    List<Customer> listCustomer = new ArrayList<>();

    public ArrayList<Customer> initialData(){
        Customer customer;

        customer = new Customer();
        customer.setcName("Joe");
        customer.setcEmail("xxx@yahoo.com");

        listCustomer.add(customer);

        return (ArrayList<Customer>) listCustomer;
    }
}
