package com.sapstrt.emanager.domain;

import java.util.Comparator;

/**
 * Created by pteltu on 10/4/13.
 */
public class ModeComparator implements Comparator<Expense> {
    public int compare(Expense e1 , Expense e2) {
        String mode1 =e1.getMode();
        String mode2 =e2.getMode();

        return mode1.compareTo(mode2);
    }
}
