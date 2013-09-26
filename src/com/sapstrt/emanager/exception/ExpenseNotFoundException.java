package com.sapstrt.emanager.exception;

/**
 * Created by cambas on 9/25/13.
 */
public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(String msg) {
        super(msg);
    }
}
