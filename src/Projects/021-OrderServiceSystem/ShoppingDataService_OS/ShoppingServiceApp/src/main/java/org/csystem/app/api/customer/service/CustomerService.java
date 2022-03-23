package org.csystem.app.api.customer.service;

import org.csystem.app.api.customer.domain.Customer;
import org.csystem.app.api.customer.domain.Phone;
import org.csystem.app.api.customer.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;
import static org.csystem.util.data.DatabaseUtil.doWorkForService;

/**
 * @author onder sahin
 */
@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    private Customer saveCustomerCallback(Customer customer)
    {
        customer.setRegisterDate(LocalDate.now());
        return customerRepository.save(customer);
    }

    private Customer updateCustomerCallback(Customer customer)
    {
        return customerRepository.save(customer);
    }

    private Customer deleteCustomerCallback(Customer customer)
    {
        customerRepository.deleteById(customer.getId());
        return customer;
    }

    public Customer saveCustomer(Customer customer)
    {
        return doWorkForService(() -> saveCustomerCallback(customer), "CustomerService.saveCustomer");
    }

    public Customer updateCustomer(Customer customer)
    {
        return doWorkForService(() -> updateCustomerCallback(customer), "CustomerService.updateCustomer");
    }


    public Customer deleteCustomer(Customer customer)
    {
        return doWorkForService(() -> deleteCustomerCallback(customer), "CustomerService.deleteCustomer");
    }

    public Optional<Customer> findCustomerById(int id)
    {
        return doWorkForRepository(() -> customerRepository.findById(id), "CustomerService.findCustomerById");
    }


}
