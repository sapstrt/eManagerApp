package com.sapstrt.emanager.service.expense;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.domain.Group;

import java.util.List;

/**
 * Created by pteltu on 11/29/13.
 */
public interface GroupService {
    public List<Group> getAllGroups();
    public void createNewGroup(Group group);
    public void updateGroup(Group group);
    public void deleteGroup(Group group);
}
