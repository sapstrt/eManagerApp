package com.sapstrt.emanager.service.preexpense.maker;

import android.telephony.SmsMessage;

import com.sapstrt.emanager.domain.Expense;

/**
 * Created by vvarma on 9/29/13.
 */
public interface ExpenseMaker {
    Expense createExpense(SmsMessage sms);
}
