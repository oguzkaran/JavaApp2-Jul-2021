package org.csystem.app.api.order.controller;

import org.csystem.app.api.invoice.controller.dto.InvoiceSaveRequestDTO;
import org.csystem.app.api.order.controller.dto.OrderSaveRequestDTO;
import org.csystem.app.api.order.controller.mapper.OrderMapper;
import org.csystem.app.api.order.service.OrderService;
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
@RequestMapping("api/orders")
public class OrderController {

    private OrderService orderService;
    private OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> saveOrder(@RequestBody OrderSaveRequestDTO orderSaveRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(orderService.saveOrder(orderMapper.toEntity(orderSaveRequestDTO)).getId())
        );
    }

    @PutMapping("/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> updateOrder(@PathVariable int id, @RequestBody OrderSaveRequestDTO orderUpdateRequestDTO)
    {
        return orderService.findOrderById(id)
                .map(order -> ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResponse<>(orderService.updateOrder(orderMapper.updateEntity(orderUpdateRequestDTO,order)).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }


    @DeleteMapping("/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> deleteOrder(@PathVariable int id)
    {
        return orderService.findOrderById(id)
                .map(customer -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                        new ApiResponse<>(orderService.deleteOrder(customer).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }
}
