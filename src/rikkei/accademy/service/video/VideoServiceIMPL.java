package rikkei.accademy.service.video;

import rikkei.accademy.config.Config;
import rikkei.accademy.model.User;
import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.model.VideoModel.Video;

import java.util.List;

public class VideoServiceIMPL implements IVideoService{
    static String PATH_VIDEO = "D:\\IdeaProjects\\MD2_Register_Role_2\\src\\rikkei\\accademy\\database\\video.txt";
    static List<Video> videoList = new Config<Video>().readFile(PATH_VIDEO);
    @Override
    public List<Video> findAll() {
        new Config<Video>().writeFile(PATH_VIDEO,videoList);
        return videoList;
    }

    @Override
    public void save(Video video) {
        videoList.add(video);
        new Config<Video>().writeFile(PATH_VIDEO,videoList);

    }

    @Override
    public Video findById(int id) {

        return videoList.stream().filter(video -> video.getId() == id).findAny().orElse(null);
    }

    @Override
    public void remove(int id) {
        videoList.remove(findById(id));
        new Config<Video>().writeFile(PATH_VIDEO,videoList);

    }


    @Override
    public void updateVideo(Video video) {
     Video video1 = findById(video.getId());
     video1.setNameVideo(video.getNameVideo());
     video1.setDateByVideo(video.getDateByVideo());
     video1.setCountry(video.getCountry());
     video1.setCategories(video.getCategories());
        new Config<Video>().writeFile(PATH_VIDEO,videoList);
    }


}
