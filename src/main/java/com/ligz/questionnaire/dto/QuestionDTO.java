package com.ligz.questionnaire.dto;

import com.ligz.questionnaire.entity.Question;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "问题表")
public class QuestionDTO {

    @ApiModelProperty(value = "问题id")
    private Long questionId;

    @NotBlank
    @ApiModelProperty(value = "问题标题")
    private String questionTitle;

    @NotNull
    @ApiModelProperty(value = "问题类型：0单选题 1多选题 2简答题")
    private Integer questionType;

    @ApiModelProperty(value = "问题的选项：1.选择题：[option1,option2,option3...]2.简答题：空字符串")
    private List<String> questionOptionList;

    private List<String> answerContent;

    public Question convertQuestion(Long paperId) {
        return Question.builder()
                       .paperId(paperId)
                       .questionTitle(questionTitle)
                       .questionType(questionType)
                       .questionOption(convertQuestionOption(questionOptionList))
                       .build();
    }

    public String convertQuestionOption(List<String> questionOption) {
        if (CollectionUtils.isEmpty(questionOption)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        questionOption.stream().forEach(option -> sb.append(option).append(","));
        return sb.toString();
    }

}
