package com.sapstrt.emanager.service.expense;

import android.content.Context;

import com.sapstrt.emanager.database.CategoryDataSource;
import com.sapstrt.emanager.database.ExpenseDataSource;
import com.sapstrt.emanager.domain.Category;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by pteltu on 11/29/13.
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDataSource categoryDataSource;
    public CategoryServiceImpl(Context context) {
        categoryDataSource = new CategoryDataSource(context);
    }


    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList=new ArrayList<>();
        categoryList=categoryDataSource.getAllCategories();
        return categoryList;
    }

    @Override
    public void createNewCategory(Category category) {
        categoryDataSource.createCategory(category);

    }
    @Override
    public void updateCategory(Category category) {
        categoryDataSource.editCategory(category);

    }


    @Override
    public void deleteCategory(Category category) {
        categoryDataSource.deleteCategory(category.getCategoryId());
    }

}
