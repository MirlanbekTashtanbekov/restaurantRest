package peaksoft.restaurantrest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantrest.entities.Subcategory;
import peaksoft.restaurantrest.service.SubcategoryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subcategories")
public class SubcategoryApi {

    private final SubcategoryService subcategoryService;

    @PostMapping
    public Subcategory createSubcategory(@RequestBody Subcategory subcategory) {
        return subcategoryService.createSubcategory(subcategory);
    }

    @GetMapping("/category/{categoryId}")
    public List<Subcategory> getSubcategoriesByCategory(@PathVariable Long categoryId) {
        return subcategoryService.getSubcategoriesByCategory(categoryId);
    }

    @DeleteMapping("/{id}")
    public void deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
    }

}
