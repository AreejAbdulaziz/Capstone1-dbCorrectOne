package com.example.capstone1db.Controller;

import com.example.capstone1db.Model.MerchantStock;
import com.example.capstone1db.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getMerchantsStocks(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStocks());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody@Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
       Integer status=merchantStockService.addMerchantStock(merchantStock);
        switch (status){
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product id");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant id");
            default:
                return ResponseEntity.status(HttpStatus.OK).body("stock added");
        }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id,@RequestBody@Valid MerchantStock merchantStock,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Integer status=merchantStockService.updateMerchantStock(id, merchantStock);
        switch (status){
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product id");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant id");
            case 3:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant stock id");
            default:
            return ResponseEntity.status(HttpStatus.OK).body("stock updated");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id){
        Boolean isDeleted=merchantStockService.deleteMerchantStock(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("stock deleted");
        }
        return ResponseEntity.status(HttpStatus.OK).body("wrong id");
    }
    @PutMapping("/addStock/{productId}/{merchantId}/{amount}")
    public ResponseEntity addStocks(@PathVariable Integer productId,@PathVariable Integer merchantId,@PathVariable Integer amount){
        Integer status=merchantStockService.addStock(productId, merchantId, amount);
        switch (status){
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product id");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant id");
            default:
                return ResponseEntity.status(HttpStatus.OK).body("stock updated");
        }
    }
}
