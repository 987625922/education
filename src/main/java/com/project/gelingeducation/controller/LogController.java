package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.service.ILogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LL
 * @Description: 错误日志的Controller
 */
@RequestMapping("/api/logs")
@RestController
public class LogController {

    @Autowired
    private ILogService logService;

    /**
     *
     * @param currentPage
     * @param pageSize
     * @return 如果有分页下标就返回分页实体类不然返回list实体
     */
    @Log("获取所有日志")
    @RequestMapping(value = "/lists")
    public Object queryAll(@RequestParam(required = false) Integer currentPage,
                           @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(logService.queryAll(currentPage, pageSize));
    }

    /**
     * @return /
     */
    @Log("删除所有info日志")
    @GetMapping("/delete_info_log")
    public Object deleteInfoLog() {
        logService.delAllByInfo();
        return JsonData.buildSuccess();
    }

    /**
     * @return /
     */
    @Log("删除所有error日志")
    @GetMapping("/delete_error_log")
    public Object deleteErrorLog() {
        logService.delAllByError();
        return JsonData.buildSuccess();
    }

    /**
     * @param id
     * @return /
     */
    @Log("删除一个日志")
    @GetMapping("/delete_one")
    public Object deleteOneLog(@RequestParam Long id) {
        logService.delOneLog(id);
        return JsonData.buildSuccess();
    }

    /**
     * @param id
     * @return /
     */
    @Log("解决了这个日志")
    @GetMapping("/solve")
    public Object solveOne(@RequestParam Long id) {
        logService.solveOne(id);
        return JsonData.buildSuccess();
    }

    /**
     * @param id
     * @return /
     */
    @Log("复现了这个日志")
    @GetMapping("/recurrent")
    public Object recurrentOne(@RequestParam Long id) {
        logService.recurrentOne(id);
        return JsonData.buildSuccess();
    }

    /**
     * 获取未解决的日志
     *
     * @param currentPage
     * @param pageSize
     * @return 分页数据
     */
    @Log("获取未解决的日志")
    @RequestMapping("/query_no_solve_log_list")
    public Object queryNoSolveLog(@RequestParam Integer currentPage
            , @RequestParam Integer pageSize) {
        return logService.queryNoSolveLog(currentPage, pageSize);
    }

    /**
     * 获取已解决的日志
     *
     * @param currentPage
     * @param pageSize
     * @return 分页数据
     */
    @Log("获取已解决的日志")
    @RequestMapping("/query_solve_log_list")
    public Object querySolveLogList(@RequestParam Integer currentPage
            , @RequestParam Integer pageSize) {
        return logService.querySolveLog(currentPage, pageSize);
    }

    /**
     * 批量删除日志
     * @param ids 日志id
     * @return
     */
    @Log("批量删除日志")
    @RequestMapping(value = "/batches_delete")
    public Object delMoreUser(@RequestParam String ids) {
        logService.delMore(ids);
        return JsonData.buildSuccess();
    }
}
