package com.example.capstone1db.Service;

import com.example.capstone1db.Model.Product;
import com.example.capstone1db.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Boolean addProduct(Product product){
        for(int i=0;i<categoryService.getCategories().size();i++){
            if(categoryService.getCategories().get(i).getId().equals(product.getCategoryID())){
                productRepository.save(product);
                return true;
            }
        } return false;
    }
    public Integer updateProduct(Integer id,Product product){
        for(int i=0;i<categoryService.getCategories().size();i++){
            if(categoryService.getCategories().get(i).getId().equals(product.getCategoryID())){
                for (int j=0;j<getProducts().size();j++){
                    if(getProducts().get(j).getId().equals(id)){
                        getProducts().get(j).setName(product.getName());
                        getProducts().get(j).setPrice(product.getPrice());
                        getProducts().get(j).setCategoryID(product.getCategoryID());

                        productRepository.save(getProducts().get(j));
                        return 0;
                    }
                } return 1; //product id
            }
        } return 2; //category id
    }
    public Boolean deleteProduct(Integer id){
        Product product=productRepository.getById(id);
        for (int j=0;j<getProducts().size();j++) {
            if (getProducts().get(j).getId().equals(id)) {
                productRepository.delete(product);
                return true;
            }
        }   return false;
    }
}
