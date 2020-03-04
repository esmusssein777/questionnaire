package com.ligz.questionnaire.service;

import com.ligz.questionnaire.dto.PaperDTO;
import com.ligz.questionnaire.entity.Paper;
import com.ligz.questionnaire.entity.Question;
import com.ligz.questionnaire.repository.PaperRepository;
import com.ligz.questionnaire.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;

    private final QuestionRepository questionRepository;

    private final QuestionService questionService;

    @Transactional
    public Long insertPaper(PaperDTO paperDTO) {
        Paper paper = Paper.builder().title(paperDTO.getTitle())
                                     .status(0)
                                     .startTime(paperDTO.getStartTime())
                                     .endTime(paperDTO.getEndTime()).build();
        final Long paperId = paperRepository.save(paper).getId();
        List<Question> questions = paperDTO.getQuestions()
                                           .stream()
                                           .map(questionDTO -> questionDTO.convertQuestion(paperId))
                                           .collect(toList());
        questionRepository.saveAll(questions);
        return paperId;
    }

    public PaperDTO getPaperById(Long paperId) {
        Paper paper = getPaper(paperId);
        return paper.convertDTO(questionService.getPaperById(paperId));
    }

    public PaperDTO getAnswerPaper(Long paperId) {
        Paper paper = getPaper(paperId);
        return paper.convertDTO(questionService.getAnswerPaper(paperId));
    }

    private Paper getPaper(Long paperId) {
        return paperRepository.findById(paperId).get();
    }

    public List<PaperDTO> getAllPaperAndQuestion() {
        return paperRepository.findAll()
                              .stream()
                              .map(paper -> paper.convertDTO(questionService.getPaperById(paper.getId())))
                              .collect(toList());
    }

    public Boolean updateStatus(Long paperId, Integer status) {
        return paperRepository.updateStatus(paperId, status) > 0;
    }
}
