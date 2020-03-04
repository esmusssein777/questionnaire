package com.ligz.questionnaire.controller;

import com.ligz.questionnaire.common.CommonResult;
import com.ligz.questionnaire.entity.Answer;
import com.ligz.questionnaire.service.AnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/answer")
@Api(value = "/answer", tags = "填写问卷API")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/reply")
    @ApiOperation(value = "填写问卷")
    public CommonResult<List<Long>> replyPaper(@RequestBody List<Answer> answer) {
        return CommonResult.success(answerService.insertAnswer(answer), "填写问卷成功");
    }
}
