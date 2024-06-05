package com.pinance.pinance;

import com.pinance.pinance.models.BankAccount;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount bankAccount;

    @Before
    public void setUp() {
        // Create an instance of the Account class before each test method
        bankAccount = new BankAccount("John Doe", BigDecimal.valueOf(1000.0));
    }

    @Test
    public void testDeposit() {
        BigDecimal depositAmount = BigDecimal.valueOf(500.0);

        // Perform deposit
        bankAccount.setAmount(bankAccount.getAmount().add(depositAmount));

        // Assert that balance is as expected
        assertEquals(BigDecimal.valueOf(1500.0), bankAccount.getAmount());
    }

    @Test
    public void testWithdrawSufficientFunds() {
        // TODO
    }

    @Test
    public void testWithdrawInsufficientFunds() {
      // TODO
    }
}

