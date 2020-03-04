package com.ligz.questionnaire.service;

import com.ligz.questionnaire.entity.Answer;
import com.ligz.questionnaire.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public List<Long> insertAnswer(List<Answer> answer) {
        List<Answer> answers = answerRepository.saveAll(answer);
        return answers.stream().map(Answer::getId).collect(toList());
    }
}
