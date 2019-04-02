package vega.it.praksa.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Category;
import vega.it.praksa.model.dtos.CategoryDto;
import vega.it.praksa.model.dtos.CategoryListDto;
import vega.it.praksa.repositories.CategoryRepository;
import vega.it.praksa.services.CategoryService;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private DtoMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, DtoMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryListDto get() {
        return new CategoryListDto(categoryRepository.findAll());
    }

    @Override
    public CategoryDto get(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent())
            return mapper.categoryToCategoryDto(category.get());
        else
            throw new NotFoundException("Category with id '" + id + "' is not found");
    }

    @Override
    public CategoryDto add(CategoryDto dto) {
        dto.setId(null);
        Category category = categoryRepository.save(mapper.categoryDtoToCategory(dto));
        return mapper.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto update(CategoryDto dto) {
        Category category = categoryRepository.save(mapper.categoryDtoToCategory(dto));
        return mapper.categoryToCategoryDto(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
