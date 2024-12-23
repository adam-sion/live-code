package adam.dev.liveCode.controller;

import adam.dev.liveCode.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class DefaultController {

    @RequestMapping("/**")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleInvalidEndpoint() {
        return new ErrorResponse("endpoint not found");
    }
}


}
