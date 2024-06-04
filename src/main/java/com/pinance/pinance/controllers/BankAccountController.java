package com.pinance.pinance.controllers;

import com.pinance.pinance.models.BankAccount;
import com.pinance.pinance.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long id) {
        Optional<BankAccount> bankAccount = bankAccountService.findById(id);
        return bankAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.save(bankAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount updatedBankAccount) {
        Optional<BankAccount> optionalBankAccount = bankAccountService.findById(id);
        if (optionalBankAccount.isPresent()) {
            BankAccount existingBankAccount = optionalBankAccount.get();
            existingBankAccount.setBankName(updatedBankAccount.getBankName());
            existingBankAccount.setAmount(updatedBankAccount.getAmount());
            BankAccount savedBankAccount = bankAccountService.save(existingBankAccount);
            return ResponseEntity.ok(savedBankAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        if (bankAccountService.findById(id).isPresent()) {
            bankAccountService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

