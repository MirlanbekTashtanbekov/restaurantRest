package peaksoft.restaurantrest.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.restaurantrest.entities.Category;
import peaksoft.restaurantrest.repo.CategoryRepo;
import peaksoft.restaurantrest.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepo categoryRepo;


    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(()->new RuntimeException("Категория не найдено"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
