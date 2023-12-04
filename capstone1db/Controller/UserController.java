package com.example.capstone1db.Controller;

import com.example.capstone1db.Model.User;
import com.example.capstone1db.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody@Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("user added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody@Valid User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Boolean isUpdated=userService.update(id, user);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("user updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        Boolean isDeleted=userService.delete(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("user deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
    @PutMapping("/buyProduct/{id}/{productId}/{merchantId}/{amount}")
    public ResponseEntity buyProduct(@PathVariable Integer id,@PathVariable Integer productId,@PathVariable Integer merchantId,@PathVariable int amount){
        int status=userService.buyProduct(id, productId, merchantId,amount);
        switch (status){
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the balance is insufficient");
            case 2:
//                Merchant merchant=merchantStockService.suggestMerchant(productId, merchantId);
//                if(merchant==null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("stock is insufficient");
//                }
//                return ResponseEntity.status(HttpStatus.OK).body("there another merchant available "+merchant);
            case 3:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant id");
            case 4:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product id");
            case 5:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong user id");
            default:
                return ResponseEntity.status(HttpStatus.OK).body("purchase completed successfully");

        }
    }
}
