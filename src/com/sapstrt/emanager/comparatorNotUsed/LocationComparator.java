package com.sapstrt.emanager.comparatorNotUsed;

import com.sapstrt.emanager.domain.Expense;

import java.util.Comparator;

/**
 * Created by pteltu on 10/4/13.
 */
public class LocationComparator implements Comparator<Expense> {
    public int compare(Expense e1 , Expense e2) {
        String loc1 =e1.getLocation();
        String loc2 =e2.getLocation();

        return loc1.compareTo(loc2);
    }
}
