package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.CategoryDto;
import vega.it.praksa.model.dtos.CategoryListDto;
import vega.it.praksa.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
        extends GenericCrudControllerImpl<
                CategoryDto, CategoryDto, CategoryListDto, Long, CategoryService> {
    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
    }
}
