package com.sapstrt.emanager.service.expense;

import com.sapstrt.emanager.domain.Category;
import com.sapstrt.emanager.domain.Expense;

import java.util.List;

/**
 * Created by pteltu on 11/29/13.
 */
public interface CategoryService {

    public List<Category> getAllCategories();
    public void createNewCategory(Category category);
    public void updateCategory(Category category);
    public void deleteCategory(Category category);
}
