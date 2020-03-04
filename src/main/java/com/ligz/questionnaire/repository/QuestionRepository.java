package com.ligz.questionnaire.repository;

import com.ligz.questionnaire.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByPaperId(Long paperId);

}
