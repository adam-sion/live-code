package adam.dev.liveCode.dao;

import adam.dev.liveCode.entity.CodeLineDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeLineDetailsRepository extends JpaRepository<CodeLineDetails, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE CodeLineDetails cld SET cld.content = :content WHERE cld.id = :id")
    int updateContent(String content, Long id);

}
