package com.example.Banking_App.service;

import com.example.Banking_App.dto.AccountDetailsDto;
import com.example.Banking_App.dto.TransactionDto;
import com.example.Banking_App.dto.TransferFundsDto;

import java.util.List;

public interface AccountService {

    AccountDetailsDto createAccount(AccountDetailsDto accountDetailsDto);

    AccountDetailsDto getAccountById(long id);

    AccountDetailsDto depositById(long id, double amount);

    AccountDetailsDto withdraw(long id, double amount);

    List<AccountDetailsDto> getAllAccount();

    void delete(long id);

    void transferFunds(TransferFundsDto transferFundsDto);

    List<TransactionDto> getALlTransaction(Long accountId);
}
