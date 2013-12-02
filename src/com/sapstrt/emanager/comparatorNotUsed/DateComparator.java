package com.sapstrt.emanager.comparatorNotUsed;

import com.sapstrt.emanager.domain.Expense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
/**
 * Created by pteltu on 10/4/13.
 */
public class DateComparator implements Comparator<Expense> {

    public int compare(Expense e1 , Expense e2) {
      SimpleDateFormat finalFmt=new SimpleDateFormat("dd-MMM-yyyy");
        Date date1= null;
        Date date2= null;
        try {
            date1 = finalFmt.parse(e1.getDate());
            date2 = finalFmt.parse(e2.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date1.after(date2) )
            return 1;
        else if(date2.after(date1))
            return -1;
        else
            return 0;
    }

}
