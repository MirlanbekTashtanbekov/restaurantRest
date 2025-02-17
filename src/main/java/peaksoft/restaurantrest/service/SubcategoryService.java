package peaksoft.restaurantrest.service;

import peaksoft.restaurantrest.entities.Subcategory;

import java.util.List;

public interface SubcategoryService {

    Subcategory createSubcategory(Subcategory subcategory);
    List<Subcategory> getSubcategoriesByCategory(Long categoryId);
    void deleteSubcategory(Long id);


}
