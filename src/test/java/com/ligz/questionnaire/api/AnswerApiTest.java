package com.ligz.questionnaire.api;

import com.google.common.collect.Lists;
import com.ligz.questionnaire.BaseApiTest;
import com.ligz.questionnaire.entity.Answer;
import com.ligz.questionnaire.repository.AnswerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerApiTest extends BaseApiTest {

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
        answerRepository.deleteAllInBatch();
    }

    @AfterEach
    void setDown() {
        answerRepository.deleteAllInBatch();
    }

    @Test
    public void should_reply_answer() {
        Answer answer1 = Answer.builder().paperId(1L).questionId(11L).questionType(2).answerContent("回答1").build();
        Answer answer2 = Answer.builder().paperId(2L).questionId(12L).questionType(2).answerContent("回答2").build();
        List<Answer> answerList = Lists.newArrayList(answer1, answer2);
        given().body(answerList)
               .when()
               .post("answer/reply")
               .then().statusCode(200)
               .extract().body().jsonPath().getList("data");
        List<Answer> answers = answerRepository.findAll();
        assertEquals(answers.size(), 2);
        assertEquals(answers.get(0).getAnswerContent(), answer1.getAnswerContent());
        assertEquals(answers.get(1).getAnswerContent(), answer2.getAnswerContent());
    }

}
