package bitter.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Bittle Not Found")
public class BittleNotFoundException extends RuntimeException {
}
