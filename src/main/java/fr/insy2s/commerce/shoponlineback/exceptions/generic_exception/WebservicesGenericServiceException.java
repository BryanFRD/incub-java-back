package fr.insy2s.commerce.shoponlineback.exceptions.generic_exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WebservicesGenericServiceException extends RuntimeException{

    private final Object data;



}

/*public class GenericServiceException extends RuntimeException {
    private final Object data;

    public GenericServiceException(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}*/
