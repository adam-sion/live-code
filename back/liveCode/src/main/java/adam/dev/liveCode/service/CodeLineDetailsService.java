package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.repository.CodeLineDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CodeLineDetailsService {

    private CodeLineDetailsRepository codeLineDetailsRepository;

    public CodeLineDetailsService(CodeLineDetailsRepository codeLineDetailsRepository) {
        this.codeLineDetailsRepository = codeLineDetailsRepository;
    }

    public void updateContent(String content, Long id) {
        if (codeLineDetailsRepository.updateContent(content, id) == 0) {
            throw new EntityNotFoundException("Code line details not found");
        }
    }

}
