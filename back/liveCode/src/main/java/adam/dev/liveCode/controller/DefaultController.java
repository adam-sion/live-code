//package adam.dev.liveCode.controller;
//
//import adam.dev.liveCode.exception.ErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class DefaultController {
//
//    @RequestMapping("/**")
//    public ResponseEntity<ErrorResponse> handleInvalidEndpoint() {
//        return new ResponseEntity<>(new ErrorResponse("Not found", "endpoint not found"), HttpStatus.NOT_FOUND);  // Correct order
//    }
//}
//
