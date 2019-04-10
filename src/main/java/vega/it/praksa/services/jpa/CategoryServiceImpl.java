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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<CategoryDto> categories=  categoryRepository.findAll()
                .stream()
                .map(mapper::categoryToCategoryDto)
                .collect(Collectors.toList());
        return new CategoryListDto(categories);
    }

    @Override
    public CategoryDto get(Long id) {
        return categoryRepository.findById(id)
                .map(mapper::categoryToCategoryDto)
                .orElseThrow(()-> new NotFoundException("Category with id '" + id +"' is not found"));
    }

    @Override
    public CategoryDto add(CategoryDto dto) {
        dto.setId(null);
        return mapper.categoryToCategoryDto(
                categoryRepository.save(mapper.categoryDtoToCategory(dto))
        );
    }

    @Override
    public CategoryDto update(CategoryDto dto) {
        return mapper.categoryToCategoryDto(
                categoryRepository.save(mapper.categoryDtoToCategory(dto))
        );
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
