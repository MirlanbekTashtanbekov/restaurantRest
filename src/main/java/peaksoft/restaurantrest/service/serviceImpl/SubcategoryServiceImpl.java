package peaksoft.restaurantrest.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.restaurantrest.entities.Subcategory;
import peaksoft.restaurantrest.repo.SubcategoryRepo;
import peaksoft.restaurantrest.service.SubcategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepo subcategoryRepo;

    @Override
    public Subcategory createSubcategory(Subcategory subcategory) {
        return subcategoryRepo.save(subcategory);
    }

    @Override
    public List<Subcategory> getSubcategoriesByCategory(Long categoryId) {
        return subcategoryRepo.findByCategoryIdOrderByName(categoryId);
    }

    @Override
    public void deleteSubcategory(Long id) {
        subcategoryRepo.deleteById(id);
    }
}
