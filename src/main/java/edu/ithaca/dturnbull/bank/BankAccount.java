package edu.ithaca.dturnbull.bank;
public class BankAccount {

    private static String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;

        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public static String getEmail(){
        return email;
    }

    public static String getPrefix(){
        String email = BankAccount.getEmail();
        String prefix="";
        for(int i=0; i<email.length(); i++){
            if(email.charAt(i) != '@'){
                prefix = prefix + email.charAt(i);
                i++;
            }
        }
        return prefix;
    }

    public static String getDomain(){
        String email = BankAccount.getEmail();
        String domain="";
        for(int i=0; i<email.length(); i++){
            i++;
            if(email.charAt(i-1) == '@'){
                domain = domain + email.charAt(i);
                i++;
            }
        }
        return domain;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if(isAmountValid(amount)){ //if amount is valid
            if (amount <= balance){
                balance -= amount;
            }
            else {
                throw new InsufficientFundsException("Not enough money");
            }
        }
        else {
            throw new IllegalArgumentException("Withdraw amount invalid");
        }
    }

    public void deposit(double amount) throws IllegalArgumentException{
        if(isAmountValid(amount)){ //if amount is valid
            if(amount==0){
                throw new IllegalArgumentException("Cannot deposit zero");
            }
        }
        else {
            throw new IllegalArgumentException("Deposit amount invalid");
        }
    }

    public void transfer(String email, double amount) throws InsufficientFundsException{
        if(isAmountValid(amount)){ //if amount is valid
            if(isEmailValid(email)){
                if(amount<= balance){
                    balance-= amount;
                }
                throw new InsufficientFundsException("not enough money");
            }
            throw new IllegalArgumentException("Email invalid");
        }
        throw new IllegalArgumentException("Transfer amount invalid");
    }

    private static boolean isPrefixValid(){
        String prefix = BankAccount.getPrefix();
        if (prefix.indexOf('@') == -1 && prefix.indexOf('#') ==-1){ //if @ and # don't appear
            if(prefix.indexOf('.') !=0 && prefix.indexOf('.') != (prefix.length()-1)){ //if a period doesn't start or end the prefix
                if(prefix.indexOf('_') !=0 && prefix.indexOf('_') != (prefix.length()-1)){ //if a _ doesn't start or end the prefix
                    if(prefix.indexOf('-') !=0 && prefix.indexOf('-') != (prefix.length()-1)){ //if a - doesn't start or end the prefix
                        for(int i=0; i<prefix.length(); i++){
                            if(prefix.charAt(i) =='.' && (prefix.charAt(i-1) =='.' || prefix.charAt(i+1) =='.')){ //if periods appear adjacent
                                return false;
                            }
                        }
                        return true;
                    }
                } 
            }
        }
        return false;
    }

    private static boolean isDomainValid(){
        String domain = BankAccount.getDomain();
        if (domain.indexOf('@') == -1 && domain.indexOf('#') ==-1){ //if @ and # don't appear
            if (domain.indexOf('.') != -1){ //if there is at least one period
                if(domain.indexOf('.') !=0 && domain.indexOf('.') != (domain.length()-1)){ //if a period doesn't start or end the domain
                    if(domain.indexOf('_') !=0 && domain.indexOf('_') != (domain.length()-1)){ //if a _ doesn't start or end the domain
                        if(domain.indexOf('-') !=0 && domain.indexOf('-') != (domain.length()-1)){ //if a - doesn't start or end the domain
                            if(domain.charAt(domain.length()-1) != '.' && domain.charAt(domain.length()-1) != '.'){ //if a period doesn't appear in last 2 characters
                                for(int i=0; i<domain.length(); i++){
                                    if(domain.charAt(i) =='.' && (domain.charAt(i-1) =='.' || domain.charAt(i+1) =='.')){ //if periods appear adjacent
                                        return false;
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isEmailValid(String email){
        if(email.indexOf('@') != email.lastIndexOf('@')){ //if @ appears more than once
            return false;
        }
        else if(isPrefixValid() && isDomainValid()){
                return true;
            }
        else{
            return false;
        }
    }

    public static boolean isAmountValid(double amount){
        if(String.valueOf(amount) == (String.format("%.2f", amount))){ //if amount is two decimal places
            if(amount<0){ //if amount is negative
                return false;
            }
            return true;
        }
        return false;
    }
}   