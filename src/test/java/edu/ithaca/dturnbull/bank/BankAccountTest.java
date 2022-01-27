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
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        
        bankAccount.withdraw(0);
        assertEquals(200, bankAccount.getBalance());
        
        bankAccount.withdraw(100);
        
        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        
        assertThrows(NegativeWithdrawalException.class, () -> bankAccount.withdraw(-1));
    }

    @Test
<<<<<<< HEAD
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

=======
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string
        assertFalse(BankAccount.isEmailValid("@b.com")); //just domain
        assertFalse(BankAccount.isEmailValid("a@")); //no domain
        assertFalse(BankAccount.isEmailValid("a@b..com")); //two periods
        assertFalse(BankAccount.isEmailValid("a@@b.com"));  //two at signs
>>>>>>> d2305264b5cfb59c335045d4afcd71da149e170d
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}