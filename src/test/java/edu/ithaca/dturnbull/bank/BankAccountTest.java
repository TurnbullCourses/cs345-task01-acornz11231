package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }
    
    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount testAccount = new BankAccount("abc@xyz.com", 1000);
        assertThrows(IllegalArgumentException.class, () -> testAccount.withdraw(0)); 
        assertThrows(IllegalArgumentException.class, () -> testAccount.withdraw(1.234)); 

        testAccount.withdraw(1);
        assertEquals(999, testAccount.getBalance());
        
        testAccount.withdraw(900);
        assertEquals(99, testAccount.getBalance());

        assertThrows(InsufficientFundsException.class, () -> testAccount.withdraw(100));
    }

    @Test
    void isEmailValidTest(){      
        
        assertFalse(BankAccount.isEmailValid("")); // empty string
        assertFalse(BankAccount.isEmailValid("abcxyz.com")); // no @

        //prefix tests
        assertFalse(BankAccount.isEmailValid("@xyz.com")); //no prefix
        assertFalse(BankAccount.isEmailValid("abc-@xyz.com")); //non-letter/number direcly before @
        assertFalse(BankAccount.isEmailValid("-abc@xyz.com")); //non-letter/number first char
        assertFalse(BankAccount.isEmailValid("a#c@xyz.com")); //invalid char... there are a number of invalid chars which one to test? multiple?
        assertFalse(BankAccount.isEmailValid("a--cb@xyz.com")); //dash followed by non-letter/number... same issue as above also need testing for ..__,.-,._ etc

        //domain tests
        assertFalse(BankAccount.isEmailValid("abc@")); //no domain
        assertFalse(BankAccount.isEmailValid("abc@xyz.c")); //last portion too short
        assertFalse(BankAccount.isEmailValid("abc@x_yz.com")); // contains invalid char, _ is valid in prefix but not in domain. 
        assertFalse(BankAccount.isEmailValid("abc@xyz")); //no last portion
        assertFalse(BankAccount.isEmailValid("abc@.com")); //no domain name
        assertFalse(BankAccount.isEmailValid("abc@xyz-.com")); //non-letter/number ending domain name


        assertTrue(BankAccount.isEmailValid("acb@xzy.com"));   // valid email address  
        assertTrue(BankAccount.isEmailValid("a.b_c@xyz.com"));

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("abc@xyzcom", -1));

        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("abc@xyzcom", 1.234));
    }

    @Test
    void isAmountValidTest() {
        assertTrue(BankAccount.isAmountValid(1));
        assertTrue(BankAccount.isAmountValid(1.2));
        assertTrue(BankAccount.isAmountValid(1.23));
        assertFalse(BankAccount.isAmountValid(1.234));
        assertTrue(BankAccount.isAmountValid(0)); // zero should be considered a negative in this case becuase you cannot add zero dollars to your bank account... you can I guess but its the same as adding nothing so zero should not be considered valid
        assertFalse(BankAccount.isAmountValid(-1));
        assertFalse(BankAccount.isAmountValid(-0.05));
    }

}