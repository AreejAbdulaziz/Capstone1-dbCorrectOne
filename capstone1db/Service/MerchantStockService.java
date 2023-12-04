package com.example.capstone1db.Service;


import com.example.capstone1db.Model.Merchant;
import com.example.capstone1db.Model.MerchantStock;
import com.example.capstone1db.Model.Product;
import com.example.capstone1db.Model.User;
import com.example.capstone1db.Repository.MerchantRepository;
import com.example.capstone1db.Repository.MerchantStockRepository;
import com.example.capstone1db.Repository.ProductRepository;
import com.example.capstone1db.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantService merchantService;
    private final ProductService productService;
    public List<MerchantStock> getMerchantStocks(){
        return merchantStockRepository.findAll();
    }
    public Integer addMerchantStock(MerchantStock merchantStock){
        for (int i=0;i<merchantService.getMerchants().size();i++){
            if(merchantService.getMerchants().get(i).getId().equals(merchantStock.getMerchantId())){
                for (int j=0;j<productService.getProducts().size();j++){
                    if(productService.getProducts().get(j).getId().equals(merchantStock.getProductId())){
                        merchantStockRepository.save(merchantStock);
                        return 0;
                    }
                } return 1; //wrong pid
            }
        } return 2; //wrong mid
    }
    public Integer updateMerchantStock(Integer id,MerchantStock merchantStock){
        for(int i=0;i< getMerchantStocks().size();i++){
            if(getMerchantStocks().get(i).getId().equals(id)){
                for (int j=0;j<merchantService.getMerchants().size();j++){
                    if(merchantService.getMerchants().get(j).getId().equals(merchantStock.getMerchantId())){
                        for (int p=0;p<productService.getProducts().size();p++){
                            if(productService.getProducts().get(p).getId().equals(merchantStock.getProductId())) {
                                for (int l=0;l<getMerchantStocks().size();l++){
                                    if(getMerchantStocks().get(l).getId().equals(id)){
                                        getMerchantStocks().get(l).setProductId(merchantStock.getProductId());
                                        getMerchantStocks().get(l).setMerchantId(merchantStock.getMerchantId());
                                        getMerchantStocks().get(l).setStock(merchantStock.getStock());
                                        merchantStockRepository.save(getMerchantStocks().get(l));
                                        return 0;
                                    }
                                }

                            }
                        }   return 1; //pid
                    }
                } return 2; // mid
            }
        } return 3; //msid
    }
    public Boolean deleteMerchantStock(Integer id){
        MerchantStock merchantStock=merchantStockRepository.getById(id);
        if(merchantStock==null){
            return false;
        }
        merchantStockRepository.delete(merchantStock);
        return true;
    }

    //endpoint
    public Integer addStock(Integer productId,Integer merchantId,Integer amount){
        for(int j=0;j<getMerchantStocks().size();j++){
            if(getMerchantStocks().get(j).getMerchantId().equals(merchantId)){
                for(int i=0;i<getMerchantStocks().size();i++) {
                    if (getMerchantStocks().get(i).getProductId().equals(productId)) {
                        MerchantStock oldMerchantStock = merchantStockRepository.getById(merchantId);
                        oldMerchantStock.setStock(oldMerchantStock.getStock() + amount);
                        merchantStockRepository.save(oldMerchantStock);
                        return 0;
                    }
                }  return 1; //pid
            }
        } return 2; //mid

    }
}
