package com.example.capstone1db.Controller;

import com.example.capstone1db.Model.Product;
import com.example.capstone1db.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody@Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Boolean isAdded=productService.addProduct(product);
        if(isAdded){
            return ResponseEntity.status(HttpStatus.OK).body("the product added successfully");
        }
        return ResponseEntity.status(HttpStatus.OK).body("wrong category id");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@RequestBody@Valid Product product,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Integer status=productService.updateProduct(id, product);
        switch (status){
            case 1:
            return ResponseEntity.status(HttpStatus.OK).body("wrong product id");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong category id");
            default:
                return ResponseEntity.status(HttpStatus.OK).body("product updated");
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        Boolean isDeleted=productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("product deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
}
