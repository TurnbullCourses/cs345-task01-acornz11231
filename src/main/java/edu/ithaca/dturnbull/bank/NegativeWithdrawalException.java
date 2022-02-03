package edu.ithaca.dturnbull.bank;

public class NegativeWithdrawalException extends Exception {
    private static final long serialVersionUID = 1L;

    public NegativeWithdrawalException(String s) {
        super(s);
    }

}

