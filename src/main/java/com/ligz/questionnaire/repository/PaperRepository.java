package com.ligz.questionnaire.repository;

import com.ligz.questionnaire.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "update paper set status = :status where id = :id")
    Integer updateStatus(@Param("id") Long paperId, @Param("status") Integer status);

}
