package com.example.capstone1db.Service;

import com.example.capstone1db.Model.MerchantStock;
import com.example.capstone1db.Model.User;
import com.example.capstone1db.Repository.MerchantStockRepository;
import com.example.capstone1db.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final MerchantStockRepository merchantStockRepository;
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public Boolean update(Integer id,User user){
        User oldUser=userRepository.getById(id);
        if(oldUser==null){
            return false;
        }
        oldUser.setUserName(user.getUserName());
        oldUser.setRole(user.getRole());
        oldUser.setBalance(user.getBalance());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());

        userRepository.save(oldUser);
        return true;
    }
    public Boolean delete(Integer id){
        User user=userRepository.getById(id);
        if(user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    public Integer buyProduct(Integer id,Integer productId,Integer merchantId,int amount){
        for(int i=0;i<getUsers().size();i++) {
            if (getUsers().get(i).getId().equals(id)) {
                for (int j = 0; j < merchantStockService.getMerchantStocks().size(); j++) {
                    if (merchantStockService.getMerchantStocks().get(j).getProductId().equals(productId)) {
                        for (int p = 0; p < merchantStockService.getMerchantStocks().size(); p++) {
                            if (merchantStockService.getMerchantStocks().get(p).getMerchantId().equals(merchantId)) {
                                if(merchantStockService.getMerchantStocks().get(p).getStock()<amount)//الستوك حق تاجر محدد
                                {
                                    return 2; // stock<amount
                                }
                                for (int a = 0; a < productService.getProducts().size(); a++)
                                {
                                    if (productService.getProducts().get(a).getId().equals(productId)) {
                                        if(productService.getProducts().get(a).getPrice()*amount > getUsers().get(i).getBalance()){
                                            return 1; //price > balance
                                        }
                                        MerchantStock oldMerchantStock = merchantStockRepository.getById(merchantId);
                                        oldMerchantStock.setStock(oldMerchantStock.getStock() - amount);
                                        merchantStockRepository.save(oldMerchantStock);
                                        User oldUser = userRepository.getById(id);
                                        oldUser.setBalance(oldUser.getBalance() - productService.getProducts().get(j).getPrice()*amount);
                                        userRepository.save(oldUser);
                                        return 0;
                                    }
                                }
                            }
                        } return 3;//wrong merchant id
                    }
                } return 4; //wrong product id
            }
        } return 5;//wrong user id
    }
}
