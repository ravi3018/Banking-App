package com.example.Banking_App.controller;

import com.example.Banking_App.dto.AccountDetailsDto;
import com.example.Banking_App.dto.TransactionDto;
import com.example.Banking_App.dto.TransferFundsDto;
import com.example.Banking_App.entity.AccountDetails;
import com.example.Banking_App.service.implementations.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("account")
public class AccountController {
    private AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDetailsDto> createAccount(@RequestBody AccountDetailsDto accountDetailsDto){
            AccountDetailsDto result= accountService.createAccount(accountDetailsDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDetailsDto> getAccount(@PathVariable long id){
        AccountDetailsDto accountDetailsDto = accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDetailsDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDetailsDto> deposit(@PathVariable long id, @RequestBody Map<String, Double> request){
        AccountDetailsDto accountDetailsDto = accountService.depositById(id,request.get("amount"));
        return ResponseEntity.ok(accountDetailsDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDetailsDto> withdraw(@PathVariable long id, @RequestBody Map<String,Double> request){
        AccountDetailsDto accountDetailsDto = accountService.withdraw(id,request.get("amount"));
        return ResponseEntity.ok(accountDetailsDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDetailsDto>> getAllAccount(){
        List<AccountDetailsDto> list = accountService.getAllAccount();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        accountService.delete(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferFundsDto transferFundsDto){
        accountService.transferFunds(transferFundsDto);
        return ResponseEntity.ok("Transferred Successfully");
    }

    @GetMapping("/{id}/transaction")
    public ResponseEntity<List<TransactionDto>> getAllTransaction(@PathVariable long id){
        List<TransactionDto> transactionList = accountService.getALlTransaction(id);

        return ResponseEntity.ok(transactionList);
    }
}
