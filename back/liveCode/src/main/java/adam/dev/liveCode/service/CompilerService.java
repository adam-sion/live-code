package adam.dev.liveCode.service;

import adam.dev.liveCode.dto.CompileCodeDTO;
import adam.dev.liveCode.dto.JDoodleResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CompilerService {

    @Value("${jdoodle.client-id}")
    private String CLIENT_ID;

    @Value("${jdoodle.client-secret}")
    private String CLIENT_SECRET;

    @Value("${jdoodle.client.api}")
    private String API_URL;


    public JDoodleResponseDTO compile(CompileCodeDTO compileCodeDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> requestBody = Map.of(
                "script", compileCodeDTO.getCode(),
                "language", compileCodeDTO.getLanguage(),
                "clientId", CLIENT_ID,
                "clientSecret", CLIENT_SECRET,
                "versionIndex", "0"
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<JDoodleResponseDTO> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, JDoodleResponseDTO.class);

        return response.getBody();
    }

}
