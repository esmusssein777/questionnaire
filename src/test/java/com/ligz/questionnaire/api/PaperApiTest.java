package com.ligz.questionnaire.api;

import com.google.common.collect.Lists;
import com.ligz.questionnaire.BaseApiTest;
import com.ligz.questionnaire.builder.PaperDTOBuilder;
import com.ligz.questionnaire.dto.PaperDTO;
import com.ligz.questionnaire.entity.Answer;
import com.ligz.questionnaire.entity.Paper;
import com.ligz.questionnaire.entity.Question;
import com.ligz.questionnaire.repository.PaperRepository;
import com.ligz.questionnaire.repository.QuestionRepository;
import com.ligz.questionnaire.service.AnswerService;
import com.ligz.questionnaire.service.PaperService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaperApiTest extends BaseApiTest {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PaperService paperService;

    @Autowired
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        paperRepository.deleteAllInBatch();
        questionRepository.deleteAllInBatch();
    }

    @AfterEach
    void setDown() {
        paperRepository.deleteAllInBatch();
        questionRepository.deleteAllInBatch();
    }

    @Test
    public void should_create_paper() {
        PaperDTO paperDTO = PaperDTOBuilder.withDefault().build();
        given().body(paperDTO)
               .when()
               .post("paper/create")
               .then().statusCode(200)
               .extract().body().jsonPath().getLong("data");
        List<Paper> papers = paperRepository.findAll();
        List<Question> questions = questionRepository.findAll();
        assertEquals(papers.size(), 1);
        assertEquals(questions.size(), 2);
        assertEquals(papers.get(0).getTitle(), paperDTO.getTitle());
        assertEquals(questions.get(0).getQuestionTitle(), paperDTO.getQuestions().get(0).getQuestionTitle());
    }

    @Test
    public void should_get_paper_with_id() {
        PaperDTO paperDTO = PaperDTOBuilder.withDefault().build();
        Long paperId = paperService.insertPaper(paperDTO);
        given().param("paperId", paperId)
               .when()
               .get("paper/get")
               .then().statusCode(200)
               .body("data.title", is("问卷1"))
               .body("data.questions[0].questionTitle", is("问题1"))
               .body("data.questions[1].questionType", is(2));
    }

    @Test
    public void should_get_answer_paper_with_id () {
        PaperDTO paperDTO = PaperDTOBuilder.withDefault().build();
        Long paperId = paperService.insertPaper(paperDTO);
        PaperDTO result = paperService.getPaperById(paperId);
        final Answer answer1 = Answer.builder()
                                     .paperId(result.getId())
                                     .questionId(result.getQuestions().get(0).getQuestionId())
                                     .questionType(2)
                                     .answerContent("answer1_1")
                                     .build();
        final Answer answer2 = Answer.builder()
                                     .paperId(result.getId())
                                     .questionId(result.getQuestions().get(0).getQuestionId())
                                     .questionType(2)
                                     .answerContent("answer1_2")
                                     .build();
        final Answer answer3 = Answer.builder()
                                     .paperId(result.getId())
                                     .questionId(result.getQuestions().get(1).getQuestionId())
                                     .questionType(2)
                                     .answerContent("answer2_1")
                                     .build();
        final Answer answer4 = Answer.builder()
                                     .paperId(result.getId())
                                     .questionId(result.getQuestions().get(1).getQuestionId())
                                     .questionType(2)
                                     .answerContent("answer2_2")
                                     .build();

        List<Answer> answers = Lists.newArrayList(answer1, answer2, answer3, answer4);
        answerService.insertAnswer(answers);
        given().param("paperId", paperId)
               .when()
               .get("paper/get-answer")
               .then().statusCode(200)
               .body("data.questions[0].answerContent[0]", is("answer1_1"))
               .body("data.questions[1].answerContent[1]", is("answer2_2"));
    }

    @Test
    public void should_get_all_paper() {
        PaperDTO paperDTO = PaperDTOBuilder.withDefault().build();
        paperService.insertPaper(paperDTO);
        given().when()
               .get("paper/get-all-paper-question")
               .then().statusCode(200)
               .body("data[0].title", is("问卷1"))
               .body("data[0].questions[0].questionTitle", is("问题1"))
               .body("data[0].questions[1].questionType", is(2));
    }

    @Test
    public void should_update_status() {
        PaperDTO paperDTO = PaperDTOBuilder.withDefault().build();
        Long paperId = paperService.insertPaper(paperDTO);
        given().param("paperId", paperId)
               .param("status", 1)
               .when()
               .get("paper/update-status")
               .then().statusCode(200)
               .body("data", is(true));
    }

}
