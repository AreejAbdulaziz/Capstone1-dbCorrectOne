package com.example.capstone1db.Controller;

import com.example.capstone1db.Model.Category;
import com.example.capstone1db.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody@Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        categoryService.addCategories(category);
        return ResponseEntity.status(HttpStatus.OK).body("category added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id ,@RequestBody@Valid Category category,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Boolean isUpdated=categoryService.updateCategory(id, category);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("category updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        Boolean isDeleted=categoryService.deleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("category deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }

}
