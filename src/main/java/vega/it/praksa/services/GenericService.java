package vega.it.praksa.services;


public interface GenericService<T_INPUT_DTO, T_OUTPUT_DTO, T_COLLECTION, IDENTIFIER>  {

    T_COLLECTION get();

    T_OUTPUT_DTO get(IDENTIFIER id);

    T_OUTPUT_DTO add(T_INPUT_DTO dto);

    T_OUTPUT_DTO update(T_INPUT_DTO dto);

    void delete(IDENTIFIER id);

}
