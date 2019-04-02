package vega.it.praksa.services;

public interface GenericService<T_DTO, T_COLLECTION, IDENTIFIER>  {

    T_COLLECTION get();

    T_DTO get(IDENTIFIER id);

    T_DTO add(T_DTO dto);

    T_DTO update(T_DTO dto);

    void delete(IDENTIFIER id);

}
