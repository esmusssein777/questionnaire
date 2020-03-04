package com.ligz.questionnaire.controller;

import com.ligz.questionnaire.common.CommonResult;
import com.ligz.questionnaire.dto.PaperDTO;
import com.ligz.questionnaire.service.PaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.ligz.questionnaire.common.CommonResult.success;

@AllArgsConstructor
@RestController
@RequestMapping("/paper")
@Api(value = "/paper", tags = "问卷API")
public class PaperController {

    private final PaperService paperService;

    @PostMapping("/create")
    @ApiOperation(value = "创建问卷")
    public CommonResult<Long> createPaper(@RequestBody @Valid PaperDTO paperDTO) {
        Long paperId = paperService.insertPaper(paperDTO);
        return success(paperId, "insert paper success");
    }

    @GetMapping("/get")
    @ApiOperation(value = "获取问卷")
    public CommonResult<PaperDTO> getPaper(@RequestParam("paperId") Long paperId) {
        return success(paperService.getPaperById(paperId), "get Paper By paperId Success");
    }

    @GetMapping("/get-answer")
    @ApiOperation(value = "获取问卷的所有答案")
    public CommonResult<PaperDTO> getAnswerPaper(@RequestParam("paperId") Long paperId) {
        return success(paperService.getAnswerPaper(paperId), "get Answer Paper success");
    }

    @GetMapping("/get-all-paper-question")
    @ApiOperation(value = "获取所有问题")
    public CommonResult<List<PaperDTO>> getAllPaperAndQuestion() {
        return success(paperService.getAllPaperAndQuestion(), "get All Paper And Question Success");
    }

    @PostMapping("/update-status")
    @ApiOperation(value = "修改发布问卷的状态")
    public CommonResult<Boolean> updateStatus(@RequestParam("paperId") Long paperId, @RequestParam("status") Integer status) {
        return success(paperService.updateStatus(paperId, status), "update status");
    }

}
