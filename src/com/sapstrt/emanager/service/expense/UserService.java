package com.sapstrt.emanager.service.expense;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.domain.User;

import java.util.List;

/**
 * Created by pteltu on 11/29/13.
 */
public interface UserService {
    public List<User> getAllUsers();
    public void createNewUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
}
