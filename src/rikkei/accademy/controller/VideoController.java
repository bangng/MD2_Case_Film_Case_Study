package rikkei.accademy.controller;

import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.service.role.IRoleService;
import rikkei.accademy.service.role.RoleServiceIMPL;
import rikkei.accademy.service.video.IVideoService;
import rikkei.accademy.service.video.VideoServiceIMPL;

import java.util.List;

public class VideoController {
    IRoleService roleService = new RoleServiceIMPL();
    IVideoService videoService = new VideoServiceIMPL();

    public List<Video> getListVideo(){
        return videoService.findAll();
    }
    public Video findByIdVieo(int id){
        return videoService.findById(id);
    }
    public void saveVideo(Video video){
        videoService.save(video);
    }
    public void deleteVideo(int id){
        videoService.remove(id);
    }

}
