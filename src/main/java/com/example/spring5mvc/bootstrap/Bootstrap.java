package com.example.spring5mvc.bootstrap;

import com.example.spring5mvc.domain.Customer;
import com.example.spring5mvc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public Bootstrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCustomers();
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Jan");
        customer1.setLastName("Kowalski");
        Customer customer2 = new Customer();
        customer2.setFirstName("Anna");
        customer2.setLastName("Drozd");
        Customer customer3 = new Customer();
        customer3.setFirstName("Janusz");
        customer3.setLastName("Cebula");

        Stream.of(customer1, customer2, customer3)
                .forEach(customerRepository::save);

    }
}
