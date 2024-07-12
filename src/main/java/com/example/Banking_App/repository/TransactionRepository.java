package com.example.Banking_App.repository;

import java.util.*;
import com.example.Banking_App.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDetails,Long> {

    List<TransactionDetails> findByAccountIdOrderByTimeStampDesc(Long id);
}
