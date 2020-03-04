package com.ligz.questionnaire.service;

import com.ligz.questionnaire.dto.QuestionDTO;
import com.ligz.questionnaire.entity.Answer;
import com.ligz.questionnaire.repository.AnswerRepository;
import com.ligz.questionnaire.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    public List<QuestionDTO> getPaperById(Long paperId) {
        return questionRepository.findByPaperId(paperId)
                                 .stream()
                                 .map(question -> question.convertDTO())
                                 .collect(toList());
    }

    public List<QuestionDTO> getAnswerPaper(Long paperId) {
        return questionRepository.findByPaperId(paperId)
                                 .stream()
                                 .map(question -> question.convertDTO())
                                 .map(questionDTO -> {
                                     questionDTO.setAnswerContent(
                                             answerRepository.findAnswersByQuestionId(questionDTO.getQuestionId())
                                                             .stream()
                                                             .map(Answer::getAnswerContent)
                                                             .collect(toList()));
                                     return questionDTO;
                                 }).collect(toList());
    }

}
