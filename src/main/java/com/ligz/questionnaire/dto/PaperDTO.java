package com.ligz.questionnaire.dto;

import com.ligz.questionnaire.entity.Paper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "调查问卷表")
public class PaperDTO {

    private Long id;

    @NotBlank
    @ApiModelProperty(value = "问卷标题")
    private String title;

    @ApiModelProperty(value = "状态值：0未发布 1已发布 2已结束")
    private Integer status;

    @NotNull
    @ApiModelProperty(value = "问卷开始时间")
    private Date startTime;

    @NotNull
    @ApiModelProperty(value = "问卷结束时间")
    private Date endTime;

    @ApiModelProperty(value = "问题列表")
    private List<QuestionDTO> questions;

    public Paper convert() {
        return Paper.builder()
                    .id(id)
                    .title(title)
                    .status(status)
                    .startTime(startTime)
                    .endTime(endTime)
                    .build();
    }

}
