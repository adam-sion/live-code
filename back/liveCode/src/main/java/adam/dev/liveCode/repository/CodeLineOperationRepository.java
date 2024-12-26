package adam.dev.liveCode.repository;

import adam.dev.liveCode.entity.CodeLineOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeLineOperationRepository extends JpaRepository<CodeLineOperation, Long> {
}
