package rikkei.accademy.service.video;

import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.service.IGenericService;

import java.util.List;

public interface IVideoService extends IGenericService<Video> {
    public void editVideo(String name, List<Category> categories);
}