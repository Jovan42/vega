package vega.it.praksa.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vega.it.praksa.model.dtos.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {
    private ErrorMessages errorMessages;

    public UnauthorizedException(String msg) {
        List<String> msgs = new ArrayList<>();
        msgs.add(msg);
        this.errorMessages = new ErrorMessages(401, "Unauthorized", msgs);
    }
}
