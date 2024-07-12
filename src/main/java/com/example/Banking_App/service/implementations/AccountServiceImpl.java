package com.example.Banking_App.service.implementations;

import com.example.Banking_App.dto.AccountDetailsDto;
import com.example.Banking_App.dto.TransactionDto;
import com.example.Banking_App.dto.TransferFundsDto;
import com.example.Banking_App.entity.AccountDetails;
import com.example.Banking_App.entity.TransactionDetails;
import com.example.Banking_App.exception.AccountException;
import com.example.Banking_App.mapper.AccountMapper;
import com.example.Banking_App.repository.AccountDetailsRepository;
import com.example.Banking_App.repository.TransactionRepository;
import com.example.Banking_App.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDetailsRepository accountDetailsRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountDetailsRepository accountDetailsRepository,
                              TransactionRepository transactionRepository) {
        this.accountDetailsRepository = accountDetailsRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountDetailsDto createAccount(AccountDetailsDto accountDetailsDto) {

        AccountDetails accountDetails = AccountMapper.mapToAccountDetails(accountDetailsDto);
        AccountDetails createdAccount = accountDetailsRepository.save(accountDetails);

        return AccountMapper.mapToAccountDetailsDto(createdAccount);
    }

    @Override
    public AccountDetailsDto getAccountById(long id) {
        AccountDetails accountDetails = accountDetailsRepository.findById(id).orElseThrow(()->
                                            new AccountException("Account does not exist"));
        return AccountMapper.mapToAccountDetailsDto(accountDetails);
    }

    @Override
    public AccountDetailsDto depositById(long id, double amount) {

        AccountDetails accountDetails = accountDetailsRepository.findById(id).orElseThrow(
                ()-> new AccountException("Account not found"));
        double newBalance = accountDetails.getBalance() + amount;
        accountDetails.setBalance(newBalance);
        AccountDetails updatedAccount = accountDetailsRepository.save(accountDetails);


        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setAccountId(id);
        transactionDetails.setAmount(amount);
        transactionDetails.setTransactionType("DEPOSIT");
        transactionDetails.setTimeStamp(LocalDateTime.now());

        transactionRepository.save(transactionDetails);

        return AccountMapper.mapToAccountDetailsDto(updatedAccount);
    }

    @Override
    public AccountDetailsDto withdraw(long id, double amount) {
        AccountDetails accountDetails = accountDetailsRepository.findById(id).orElseThrow(
                ()-> new AccountException("Account not found") );
        double balance = accountDetails.getBalance();
        if(balance < amount){
            throw new RuntimeException("Not enough balance");
        }
        double newBalance = balance - amount;
        accountDetails.setBalance(newBalance);
        AccountDetails updatedAccount = accountDetailsRepository.save(accountDetails);

        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setAccountId(id);
        transactionDetails.setAmount(amount);
        transactionDetails.setTransactionType("WITHDRAW");
        transactionDetails.setTimeStamp(LocalDateTime.now());

        transactionRepository.save(transactionDetails);

        return AccountMapper.mapToAccountDetailsDto(updatedAccount);
    }

    @Override
    public List<AccountDetailsDto> getAllAccount() {
        List<AccountDetails> listOfAccounts = accountDetailsRepository.findAll();

        return listOfAccounts.stream()
                                             .map((account)-> AccountMapper.mapToAccountDetailsDto(account))
                                            .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        AccountDetails accountDetails = accountDetailsRepository.findById(id).orElseThrow(
                ()-> new AccountException("Account not found"));

        accountDetailsRepository.delete(accountDetails);
    }

    @Override
    public void transferFunds(TransferFundsDto transferFundsDto) {
        AccountDetails sender = accountDetailsRepository.findById(transferFundsDto.sender())
                .orElseThrow(()-> new AccountException("Account Not Found"));
        AccountDetails receiver = accountDetailsRepository.findById(transferFundsDto.receiver())
                .orElseThrow(()-> new AccountException("Account not Found"));
        sender.setBalance(sender.getBalance()- transferFundsDto.funds());
        receiver.setBalance(receiver.getBalance() + transferFundsDto.funds());
        accountDetailsRepository.save(sender);
        accountDetailsRepository.save(receiver);

        TransactionDetails transactionDetails1 = new TransactionDetails();
        transactionDetails1.setAccountId(sender.getId());
        transactionDetails1.setAmount(transferFundsDto.funds());
        transactionDetails1.setTransactionType("SEND");
        transactionDetails1.setTimeStamp(LocalDateTime.now());

        TransactionDetails transactionDetails2 = new TransactionDetails();
        transactionDetails2.setAccountId(receiver.getId());
        transactionDetails2.setAmount(transferFundsDto.funds());
        transactionDetails2.setTransactionType("RECEIVED");
        transactionDetails2.setTimeStamp(LocalDateTime.now());

        transactionRepository.save(transactionDetails1);
        transactionRepository.save(transactionDetails2);

    }

    @Override
    public List<TransactionDto> getALlTransaction(Long accountId) {

        List<TransactionDetails> transactionDetails = transactionRepository.findByAccountIdOrderByTimeStampDesc(accountId);

        return transactionDetails.stream().map(
                (transaction) -> convertEntityToDto(transaction))
                .collect(Collectors.toList());

    }

    private TransactionDto convertEntityToDto(TransactionDetails transactionDetails){
        return new TransactionDto(
                transactionDetails.getTransactionId(),
                transactionDetails.getAccountId(),
                transactionDetails.getAmount(),
                transactionDetails.getTransactionType(),
                transactionDetails.getTimeStamp()
        );
    }
}
