package org.csystem.app.api.product.service;

import org.csystem.app.api.product.domain.Product;
import org.csystem.app.api.product.domain.ProductCategory;
import org.csystem.app.api.product.repository.IProductCategoryRepository;
import org.csystem.app.api.product.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;
import static org.csystem.util.data.DatabaseUtil.doWorkForService;

/**
 * @author onder sahin
 */
@Service
public class ProductService {

    private final IProductRepository productRepository;
    private final IProductCategoryRepository productCategoryRepository;

    public ProductService(IProductRepository productRepository, IProductCategoryRepository productCategoryRepository)
    {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    public Optional<ProductCategory> findProductCategoryById(int id)
    {
        return doWorkForRepository(() -> productCategoryRepository.findById(id), "ProductService.findProductCategoryById");
    }

    private Product saveProductCallback(Product product)
    {
        return productRepository.save(product);
    }

    private Product updateProductCallback(Product product)
    {
        return productRepository.save(product);
    }

    private Product deleteProductCallback(Product product)
    {
        productRepository.deleteById(product.getId());
        return product;
    }

    public Product saveProduct(Product product)
    {
        return doWorkForService(() -> saveProductCallback(product), "ProductService.saveProduct");
    }


    public Product updateProduct(Product product)
    {
        return doWorkForService(() -> updateProductCallback(product), "ProductService.updateProduct");
    }


    public Product deleteProduct(Product product)
    {
        return doWorkForService(() -> deleteProductCallback(product), "ProductService.deleteProduct");
    }

    public Optional<Product> findProductById(int id)
    {
        return doWorkForRepository(() -> productRepository.findById(id), "ProductService.findProductById");
    }
}
