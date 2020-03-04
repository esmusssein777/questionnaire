package com.ligz.questionnaire.builder;

import com.ligz.questionnaire.dto.QuestionDTO;

import java.util.List;

public class QuestionDTOBuilder {

    private QuestionDTO questionDTO;

    private QuestionDTOBuilder() {
        this.questionDTO = new QuestionDTO();
    }

    public static QuestionDTOBuilder newQuestionDTO() {
        return new QuestionDTOBuilder();
    }

    public static QuestionDTOBuilder withDefault() {
        return withDefault("问题1");
    }

    public static QuestionDTOBuilder withDefault(String questionTitle) {
        QuestionDTOBuilder questionDTOBuilder = new QuestionDTOBuilder();
        questionDTOBuilder.questionDTO = QuestionDTO.builder()
                                                    .questionTitle(questionTitle)
                                                    .questionType(2)
                                                    .build();
        return questionDTOBuilder;
    }

    public QuestionDTO build() {
        return questionDTO;
    }

    public QuestionDTOBuilder withQuestionTitle(String questionTitle) {
        questionDTO.setQuestionTitle(questionTitle);
        return this;
    }

    public QuestionDTOBuilder withQuestionType(Integer questionType) {
        questionDTO.setQuestionType(questionType);
        return this;
    }

    public QuestionDTOBuilder withQuestionOptionList(List<String> questionOptionList) {
        questionDTO.setQuestionOptionList(questionOptionList);
        return this;
    }

    public QuestionDTOBuilder withAnswerContent(List<String> answerContent) {
        questionDTO.setAnswerContent(answerContent);
        return this;
    }
}
