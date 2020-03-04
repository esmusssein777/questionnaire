package com.ligz.questionnaire.entity;

import com.ligz.questionnaire.dto.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Arrays;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "paper_id")
    private Long paperId;

    @Column(name = "question_type")
    private Integer questionType;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "question_option")
    private String questionOption;

    @Column(name = "create_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createTime;

    @Column(name = "updated_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedTime;

    public QuestionDTO convertDTO() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(id);
        questionDTO.setQuestionTitle(questionTitle);
        questionDTO.setQuestionType(questionType);
        if (isNotBlank(questionOption)) {
            questionDTO.setQuestionOptionList(Arrays.asList(questionOption.split(",")));
        }
        return questionDTO;
    }

}
