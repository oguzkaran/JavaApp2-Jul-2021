package org.csystem.app.api.product.controller;

import org.csystem.app.api.product.controller.dto.ProductSaveRequestDTO;
import org.csystem.app.api.product.controller.mapper.ProductMapper;
import org.csystem.app.api.product.service.ProductService;
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
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }


    @PostMapping
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> saveCustomer(@RequestBody ProductSaveRequestDTO productSaveRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(productService.saveProduct(productMapper.toEntity(productSaveRequestDTO)).getId())
        );
    }


    @PutMapping("/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> updateCustomer(@PathVariable int id, @RequestBody ProductSaveRequestDTO productUpdateRequestDTO)
    {
        return productService.findProductById(id)
                .map(product -> ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResponse<>(productService.updateProduct(productMapper.updateEntity(productUpdateRequestDTO,product)).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }


    @DeleteMapping("/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> deleteCustomer(@PathVariable int id)
    {
        return productService.findProductById(id)
                .map(product -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                        new ApiResponse<>(productService.deleteProduct(product).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }


}
