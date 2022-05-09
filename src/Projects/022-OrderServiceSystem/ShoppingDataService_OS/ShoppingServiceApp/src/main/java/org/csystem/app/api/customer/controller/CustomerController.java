package org.csystem.app.api.customer.controller;

import org.csystem.app.api.customer.controller.dto.CustomerPhoneSaveRequestDTO;
import org.csystem.app.api.customer.controller.dto.CustomerSaveRequestDTO;
import org.csystem.app.api.customer.controller.dto.CustomerUpdateRequestDTO;
import org.csystem.app.api.customer.controller.mapper.CustomerMapper;
import org.csystem.app.api.customer.controller.mapper.CustomerPhoneMapper;
import org.csystem.app.api.customer.service.CustomerService;
import org.csystem.app.api.customer.service.PhoneService;
import org.csystem.app.pdb.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;

/**
 * @author onder sahin
 */
@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private CustomerService customerService;
    private PhoneService phoneService;
    private CustomerMapper customerMapper;
    private CustomerPhoneMapper customerPhoneMapper;

    public CustomerController(CustomerService customerService, PhoneService phoneService, CustomerMapper customerMapper, CustomerPhoneMapper customerPhoneMapper)
    {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.phoneService = phoneService;
        this.customerPhoneMapper = customerPhoneMapper;

    }

    @GetMapping("test")
    public String test(){
        return "Test";
    }


    @PostMapping
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> saveCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(customerService.saveCustomer(customerMapper.toEntity(customerSaveRequestDTO)).getId())
        );
    }

    @PutMapping("/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> updateCustomer(@PathVariable int id, @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO)
    {
        return customerService.findCustomerById(id)
                .map(customer -> ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(customerService.updateCustomer(customerMapper.updateEntity(customerUpdateRequestDTO,customer)).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }


    @DeleteMapping("/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> deleteCustomer(@PathVariable int id)
    {
        return customerService.findCustomerById(id)
                .map(customer -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                        new ApiResponse<>(customerService.deleteCustomer(customer).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }


    @PostMapping("phones")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> saveCustomerPhone(@RequestBody CustomerPhoneSaveRequestDTO phoneSaveRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(phoneService.savePhone(customerPhoneMapper.toEntity(phoneSaveRequestDTO)).getId())
        );
    }


    @PutMapping("phones/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> updateCustomerPhone(@PathVariable int id, @RequestBody CustomerPhoneSaveRequestDTO phoneUpdateRequestDTO)
    {
        return phoneService.findPhoneById(id)
                .map(phone -> ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResponse<>(phoneService.updatePhone(customerPhoneMapper.updateEntity(phoneUpdateRequestDTO,phone)).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }


    @DeleteMapping("phones/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> deleteCustomerPhone(@PathVariable int id)
    {
        return phoneService.findPhoneById(id)
                .map(phone -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                        new ApiResponse<>(phoneService.deletePhone(phone).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }


}
