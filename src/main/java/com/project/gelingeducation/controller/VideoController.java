package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonResult;
import com.project.gelingeducation.entity.Video;
import com.project.gelingeducation.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @Author: LL
 * @Description: 视频的Controller
 */
@RequestMapping("/api/video")
@RestController
@Slf4j
public class VideoController {

    /**
     * 视频实体类的service
     */
    @Autowired
    private IVideoService videoService;

    /**
     * 分页获取视频的实体类lists
     * 如果current为空就获取全部
     *
     * @param currentPage
     * @param pageSize
     * @return 视频的实体类lists
     */
    @Log("获取视频列表")
    @RequestMapping(value = "/lists")
    public Object queryAll(@RequestParam(required = false) Integer currentPage,
                           @RequestParam(required = false) Integer pageSize) {
        return JsonResult.buildSuccess(videoService.queryAll(currentPage, pageSize));
    }

    /**
     * 通过id获取视频
     *
     * @param id
     * @return 视频实体类
     */
    @Log("通过id获取视频")
    @RequestMapping(value = "/find_by_id")
    public Object findById(Long id) {
        return videoService.findById(id);
    }

    /**
     * 添加视频
     *
     * @param video
     * @return /
     */
    @Log("添加视频")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestBody Video video) {
        videoService.insert(video);
        return JsonResult.buildSuccess();
    }

    /**
     * 删除视频
     *
     * @param id
     * @return /
     */
    @Log("删除视频")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        videoService.delete(id);
        return JsonResult.buildSuccess();
    }

    /**
     * 更新视频
     *
     * @param video
     * @return /
     */
    @Log("更新视频")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody Video video) {
        videoService.update(video);
        return JsonResult.buildSuccess();
    }

    /**
     * 批量删除视频
     *
     * @param ids 视频id
     * @return /
     */
    @Log("批量删除视频")
    @RequestMapping(value = "/batches_delete")
    public Object delMoreUser(@RequestParam String ids) {
        videoService.delMore(ids);
        return JsonResult.buildSuccess();
    }

    /**
     * 按条件搜索视频列表
     *
     * @param teacherId 教师id
     * @param name      视频名
     * @param currentPage 页码
     * @param pageSize    页数
     * @param courseIds 1,2,3格式的课程id字符串
     * @return 分页的视频list列表
     */
    @Log("按条件搜索视频列表")
    @RequestMapping("/search_by_criteria")
    public Object searchByCriteria(@RequestParam(required = false) String teacherId,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String courseIds,
                                   @RequestParam Integer currentPage, @RequestParam Integer pageSize) throws UnsupportedEncodingException {
        return JsonResult.buildSuccess(videoService.searchByCriteria(teacherId,
                name, courseIds,currentPage,pageSize));
    }
}
