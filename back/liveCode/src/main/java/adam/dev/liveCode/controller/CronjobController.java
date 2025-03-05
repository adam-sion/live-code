package adam.dev.liveCode.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cronjob")
public class CronjobController {

    @GetMapping
    public ResponseEntity<Void> cronjob() {
        return ResponseEntity.ok()
                .build();
    }

}
