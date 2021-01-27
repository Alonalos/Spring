package com.cybertek.controller;

import com.cybertek.entity.Product;
import com.cybertek.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerPreferred {
    private ProductService productService;

    public ProductControllerPreferred(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody Product getProduct(@PathVariable("id") long id){

        return productService.getProduct(id);
    }

    @GetMapping
    public @ResponseBody List<Product> getProducts(){

        return productService.getProducts();
    }

    @PostMapping
    public @ResponseBody List<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody List<Product> deleteProduct(@PathVariable("id") long id){
        return productService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody List<Product> updateProduct(@PathVariable("id") long id,@RequestBody Product product){
        return productService.updateProduct(id, product);
    }
}
