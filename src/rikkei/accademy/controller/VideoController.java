package rikkei.accademy.controller;

import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.service.categoryvideo.CategoryServiceIMPL;
import rikkei.accademy.service.categoryvideo.ICategoryService;
import rikkei.accademy.service.role.IRoleService;
import rikkei.accademy.service.role.RoleServiceIMPL;
import rikkei.accademy.service.video.IVideoService;
import rikkei.accademy.service.video.VideoServiceIMPL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoController {
    IRoleService roleService = new RoleServiceIMPL();
    IVideoService videoService = new VideoServiceIMPL();
    ICategoryService categoryService =new CategoryServiceIMPL();

    public List<Video> getListVideo() {
        return videoService.findAll();

    }

    public Video findByIdVideo(int id) {
        return videoService.findById(id);
    }

    public void createVideo(Video video) {
        videoService.save(video);
    }

    public void deleteVideo(int id) {
        videoService.remove(id);
    }


    public List<Video> showVideoWithCategory(int id){
        Category category1 = categoryService.findById(id);
        List<Video> videoList = new ArrayList<>();
        for (Video video: videoService.findAll()) {
            for (Category category: video.getCategories()) {
                if (category1 == category){
                    videoList.add(video);
                }
            }
        }

        return videoList;
    }

    public void editVideo(Video video1) {
        videoService.updateVideo(video1);
    }
}
