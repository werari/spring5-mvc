package com.example.spring5mvc.service;

import com.example.spring5mvc.api.v1.mapper.CustomerMapper;
import com.example.spring5mvc.api.v1.model.CustomerDTO;
import com.example.spring5mvc.controller.CustomerController;
import com.example.spring5mvc.domain.Customer;
import com.example.spring5mvc.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;
    CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        //alternatywa  MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    public void getAllCustomers() {
        //given
        Customer customer1 = Customer.builder()
                .id(1L)
                .firstName("Piotr")
                .lastName("Nowak")
                .build();
        Customer customer2 = Customer.builder()
                .id(2L)
                .firstName("Aneta")
                .lastName("Kwiat")
                .build();

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        //when
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        //then
        assertEquals(2, allCustomers.size());

    }

    @Test
    public void getCustomerById() {
        //given
        Customer customer1 = Customer.builder()
                .id(1L)
                .firstName("Piotr")
                .lastName("Nowak")
                .build();
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));
        //when
        CustomerDTO customerById = customerService.getCustomerById(1L);
        //then
        assertEquals("Piotr", customerById.getFirstName());
        assertEquals(CustomerController.BASE_URL + "/1", customerById.getCustomerUrl());
    }

    @Test
    public void createNewCustomer() {

        //todo napisac test
    }
}