package com.sapstrt.emanager.service.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.google.android.gms.auth.GoogleAuthUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pteltu on 11/26/13.
 */
public class GoogleAccountNames {

    Context context;
    public GoogleAccountNames(Context context){
        this.context=context;
    }


    public List<String> getAccountNames() {
        AccountManager mAccountManager = AccountManager.get(context);
        Account[] accounts = mAccountManager.getAccountsByType(
                GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
       List<String> accList=new ArrayList<>();
        for (int i = 0; i < accounts.length; i++) {
            accList.add(accounts[i].name);
        }
        return accList;

    }
}

