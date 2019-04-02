package vega.it.praksa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.CategoryDto;
import vega.it.praksa.model.dtos.CategoryListDto;
import vega.it.praksa.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends GenericCrudControllerImpl<CategoryDto, CategoryListDto, Long, CategoryService> {

    public CategoryController(CategoryService categoryService) {
        super(categoryService);
    }
}
