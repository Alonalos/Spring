package com.cybertek.controller;

import com.cybertek.entity.Product;
import com.cybertek.entity.ResponseWrapper;
import com.cybertek.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable("id") long id){

        return productService.getProduct(id);
    }

    @GetMapping
    public @ResponseBody List<Product> getProducts(){

        List<Product> list =productService.getProducts();
        return list;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<List<Product>> createProduct(@RequestBody Product product){
        List<Product> set=productService.createProduct(product);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Version","Cybertek.V1")
                .header("Operation","Create")
                .body(set);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<List<Product>>deleteProduct(@PathVariable("id") long id){
        HttpHeaders responseHttpHeaders=new HttpHeaders();
        responseHttpHeaders.set("Version","Cybertek.v1");
        responseHttpHeaders.set("Operation","Get list");

        List<Product> list=productService.delete(id);
        return new ResponseEntity<>(list,responseHttpHeaders,HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<List<Product>> updateProduct(@PathVariable("id") long id,@RequestBody Product product){
        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.add("Version","Cybertek.V1");
        map.add("Operation","Update");
        List<Product> list=productService.updateProduct(id,product);

        return new ResponseEntity<>(list,map,HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<ResponseWrapper> readAllProducts(){
        return ResponseEntity
                .ok(new ResponseWrapper("products successfully retrieved",productService.getProducts()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteProduct2(@PathVariable("id") long id){
        return ResponseEntity.ok(new ResponseWrapper("product successfully deleted"));
    }

    @DeleteMapping("/delete2/{id}")
    public  ResponseEntity<ResponseWrapper> deleteProduct3(@PathVariable("id")long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseWrapper("product successfully deleted"));
    }
}
