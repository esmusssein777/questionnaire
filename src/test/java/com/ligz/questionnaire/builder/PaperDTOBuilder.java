package com.ligz.questionnaire.builder;

import com.ligz.questionnaire.dto.PaperDTO;
import com.ligz.questionnaire.dto.QuestionDTO;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PaperDTOBuilder {

    private PaperDTO paperDTO;

    private PaperDTOBuilder() {
        this.paperDTO = new PaperDTO();
    }

    public static PaperDTOBuilder newPaperDTO() {
        return new PaperDTOBuilder();
    }

    public static PaperDTOBuilder withDefault() {
        PaperDTOBuilder paperDTOBuilder = new PaperDTOBuilder();
        List<QuestionDTO> questions = newArrayList(QuestionDTOBuilder.withDefault("问题1").build(),QuestionDTOBuilder.withDefault("问题2").build());

        paperDTOBuilder.paperDTO = PaperDTO.builder()
                                           .title("问卷1")
                                           .questions(questions)
                                           .status(1)
                                           .startTime(new Date())
                                           .endTime(new Date())
                                           .build();
        return paperDTOBuilder;
    }

    public PaperDTO build() {
        return paperDTO;
    }

    public PaperDTOBuilder withTitle(String title) {
        paperDTO.setTitle(title);
        return this;
    }

    public PaperDTOBuilder withStatus(Integer status) {
        paperDTO.setStatus(status);
        return this;
    }

    public PaperDTOBuilder withStartTime(Date startTime) {
        paperDTO.setStartTime(startTime);
        return this;
    }

    public PaperDTOBuilder withEndTime(Date endTime) {
        paperDTO.setEndTime(endTime);
        return this;
    }

    public PaperDTOBuilder withQuestionDTO(List<QuestionDTO> questions) {
        paperDTO.setQuestions(questions);
        return this;
    }
}
