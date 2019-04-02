package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.CategoryDto;
import vega.it.praksa.model.dtos.CategoryListDto;
import vega.it.praksa.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController implements GenericCrudController<CategoryDto, CategoryListDto, Long> {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<CategoryListDto> get() {
        return new ResponseEntity<>(categoryService.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> get(Long id) {
        return new ResponseEntity<>(categoryService.get(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> add(CategoryDto categoryDto, Errors errors) {
        return new ResponseEntity<>(categoryService.add(categoryDto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CategoryDto> update(CategoryDto categoryDto, Errors errors) {
        return new ResponseEntity<>(categoryService.update(categoryDto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
