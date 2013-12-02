package com.sapstrt.emanager.service.util;

import android.content.Context;
import android.content.Intent;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.domain.User;
import com.sapstrt.emanager.service.configuration.SettingsFileWriter;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;
import com.sapstrt.emanager.service.expense.UserService;
import com.sapstrt.emanager.service.expense.UserServiceImpl;
import com.sapstrt.emanager.service.preexpenseNotUsed.maker.ExpenseMakerService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pteltu on 11/28/13.
 */
public class AppFirstTimeUseService {
    HTTPService httpService=HTTPService.getInstance();
    ImportSms smsImporter = ImportSms.getInstance();
    SettingsFileWriter settingsFileWriter=new SettingsFileWriter();
    Context context;
    public AppFirstTimeUseService(Context context){
        this.context=context;
    }

    public void getStarted(){

        String token=getToken();
        try {

             httpService.sendTokenToServer(token);
             addUserInLocalDb();

        } catch (IOException e) {
            e.printStackTrace();
        }

            Integer UserGrpId=httpService.getGrpIdFromServer(token);// grpId which only user uses (grp having only 1 user)
            Map<String, String> GrpIdMap = new TreeMap<>();
            GrpIdMap.put("User Group ID",UserGrpId.toString());
            settingsFileWriter.writeToSettingsFile(context,GrpIdMap);

            Intent serviceStarter=new Intent(context, ExpenseMakerService.class);
            serviceStarter.putExtra("messages",smsImporter.getSmsList());
            context.startService(serviceStarter);

            ExpenseService expenseService=new ExpenseServiceImpl(context);
            List<Expense> expenses =expenseService.getAllExpenses();
            httpService.sendExpensesToServer(token, expenses);



    }

    public String getToken(){
        GenerateTokenService generateTokenService=new GenerateTokenService();
        String token=generateTokenService.getToken(context);
        return token;
    }
    public void addUserInLocalDb(){
        User user=new User();
        String emailId=settingsFileWriter.getDataFromSettingsFile(context,"GoogleAccount");
        user.setEmailId(emailId);
        UserService userService=new UserServiceImpl(context);
        userService.createNewUser(user);
    }
}
