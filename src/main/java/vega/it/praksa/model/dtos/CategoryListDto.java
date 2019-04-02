package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import vega.it.praksa.model.Category;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDto {
    private List<Category> categories;
}
