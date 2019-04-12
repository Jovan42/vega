package vega.it.praksa.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vega.it.praksa.model.dtos.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BadRequestException extends RuntimeException {
    private ErrorMessages errorMessages;

    public BadRequestException(String message) {
        List<String> msgs = new ArrayList<>();
        msgs.add(message);
        this.errorMessages = new ErrorMessages(400, "Bad Request", msgs);
    }

    public BadRequestException(List<String> messages) {
        this.errorMessages = new ErrorMessages(400, "Bad Request", messages);
    }
}
