package com.sapstrt.emanager.service.expense;

import android.content.Context;

import com.sapstrt.emanager.database.CategoryDataSource;
import com.sapstrt.emanager.database.GroupDataSource;
import com.sapstrt.emanager.domain.Category;
import com.sapstrt.emanager.domain.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pteltu on 11/29/13.
 */
public class GroupServiceImpl implements GroupService{
    private GroupDataSource groupDataSource;
    public GroupServiceImpl(Context context) {
        groupDataSource = new GroupDataSource(context);
    }



    @Override
    public List<Group> getAllGroups() {
        return groupDataSource.getAllGrps();
    }

    @Override
    public void createNewGroup(Group group) {
        groupDataSource.createGroup(group);
    }

    @Override
    public void updateGroup(Group group) {
        groupDataSource.editGroup(group);
    }

    @Override
    public void deleteGroup(Group group) {
        groupDataSource.deleteGroup(group.getId());

    }
}
