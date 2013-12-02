package com.sapstrt.emanager.comparatorNotUsed;

import com.sapstrt.emanager.domain.Expense;

import java.util.Comparator;

/**
 * Created by pteltu on 10/4/13.
 */
public class AmountComparator implements Comparator<Expense> {

    public int compare(Expense e1 , Expense e2) {
         Double amt1 =e1.getAmount();
        Double amt2 =e2.getAmount();

        if(amt1>amt2)
            return 1;
        else if(amt1<amt2)
            return -1;
        else
            return 0;
    }
}
