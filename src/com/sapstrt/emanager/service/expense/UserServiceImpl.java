package com.sapstrt.emanager.service.expense;

import android.content.Context;

import com.sapstrt.emanager.database.GroupDataSource;
import com.sapstrt.emanager.database.UserDataSource;
import com.sapstrt.emanager.domain.Group;
import com.sapstrt.emanager.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pteltu on 11/29/13.
 */
public class UserServiceImpl implements UserService{
    private UserDataSource userDataSource;
    public UserServiceImpl(Context context) {
        userDataSource = new UserDataSource(context);
    }


    @Override
    public List<User> getAllUsers() {
        return userDataSource.getAllUsers();

    }

    @Override
    public void createNewUser(User user) {
        userDataSource.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDataSource.editUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDataSource.deleteUser(user.getUserId());
    }
}
