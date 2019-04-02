package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import vega.it.praksa.model.Client;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientListDto {
    private List<Client> clients;
}
