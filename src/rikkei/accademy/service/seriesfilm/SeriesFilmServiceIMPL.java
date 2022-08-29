package rikkei.accademy.service.seriesfilm;

import rikkei.accademy.config.Config;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.model.seriesfilm.Series;
import rikkei.accademy.service.video.IVideoService;
import rikkei.accademy.service.video.VideoServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class SeriesFilmServiceIMPL implements ISeriesFilmService{
    IVideoService videoService = new VideoServiceIMPL();
    static String PATH_SERIESFILM = "D:\\IdeaProjects\\MD2_Register_Role_2\\src\\rikkei\\accademy\\database\\seriesfilm.txt";
    static List<Series> series = new Config<Series>().readFile(PATH_SERIESFILM);
    @Override
    public List<Series> findAll() {
        new Config<Series>().writeFile(PATH_SERIESFILM,series);
        return series;
    }

    @Override
    public void save(Series series1) {
       series.add(series1);

        new Config<Series>().writeFile(PATH_SERIESFILM,series);
    }

    @Override
    public Series findById(int id) {

        return series.stream().filter(series -> series.getId() == id).findAny().orElse(null);
    }

    @Override
    public void remove(int id) {
        series.remove(findById(id));
        new Config<Series>().writeFile(PATH_SERIESFILM,series);

    }
}
