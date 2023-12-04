package com.example.capstone1db.Repository;

import com.example.capstone1db.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant,Integer> {
}
