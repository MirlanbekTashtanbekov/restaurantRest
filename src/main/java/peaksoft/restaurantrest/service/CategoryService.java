package peaksoft.restaurantrest.service;

import peaksoft.restaurantrest.entities.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void deleteCategory(Long id);

}
