package adam.dev.liveCode.dao.repository;

import adam.dev.liveCode.entity.CodeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeLineRepository extends JpaRepository<CodeLine, Long> {

    @Modifying
    @Query("UPDATE CodeLineDetails c SET c.lineNumber = c.lineNumber + :toAdd " +
            "WHERE c.lineNumber >= :newLineNumber " +
            "AND c IN (SELECT cl.codeLineDetails FROM CodeLine cl WHERE cl.roomCode.id = :roomCodeId)")
    void updateLineNumbersAfter(int newLineNumber, Long roomCodeId, int toAdd);

}
