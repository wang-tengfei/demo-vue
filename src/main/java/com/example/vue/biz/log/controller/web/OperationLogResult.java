package com.example.vue.biz.log.controller.web;

import com.example.vue.biz.log.domain.OperationLog;
import com.example.vue.common.constant.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 4:57 下午 2/1/2020
 * @modified by:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperationLogResult extends Result {

    @Schema(ref = "#/components/schemas/UserInfo")
    private String testId;

    @Schema(description = "this is response result", allowableValues =  {"available","pending","sold"})
    private List<OperationLog> result;

}
