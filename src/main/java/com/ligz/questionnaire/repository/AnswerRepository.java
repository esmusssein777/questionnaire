package com.ligz.questionnaire.repository;

import com.ligz.questionnaire.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAnswersByQuestionId(Long questionId);

}
