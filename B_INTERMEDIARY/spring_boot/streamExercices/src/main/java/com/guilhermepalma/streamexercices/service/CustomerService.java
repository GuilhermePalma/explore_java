package com.guilhermepalma.streamexercices.service;

import com.guilhermepalma.streamexercices.data.model.Customer;
import com.guilhermepalma.streamexercices.data.repository.CustomerRepository;
import com.guilhermepalma.streamexercices.util.Util;
import com.guilhermepalma.streamexercices.view.GridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public GridView<Customer> getCustomers() {
        List<Customer> results = customerRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

}
