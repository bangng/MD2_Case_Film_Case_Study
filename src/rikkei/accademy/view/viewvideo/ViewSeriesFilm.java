package rikkei.accademy.view.viewvideo;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.SeriesFilmController;
import rikkei.accademy.controller.VideoController;
import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.model.seriesfilm.Series;

import java.util.ArrayList;
import java.util.List;

public class ViewSeriesFilm {
    SeriesFilmController seriesFilmController = new SeriesFilmController();
    VideoController videoController = new VideoController();
    List<Series> seriesList = seriesFilmController.getListSeries();
    List<Video> videoList = videoController.getListVideo();
    public void seriesMenu(){
        System.out.println("*****************Menu SeriesFilm******************");
        System.out.println("1: Show list Series film");
        System.out.println("2: Create Series film");

        int choice = 0;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            seriesMenu();
        }
        switch (choice) {
            case 1:
                formShowListSeriesFilm();
                break;
            case 2:
                formCreateSeriesFilm();
                break;
        }

    }

    private void selectVideo(List<Video> listSelectVideo) {
        for (int i = 0; i < videoController.phimBo().size(); i++) {
            System.out.println("**" + videoList.get(i).getId() + "**" +videoList.get(i).getNameVideo());
        }
        System.out.println("Enter id of video to select:");
        int idSelect = Config.scanner().nextInt();
       Video video = videoController.findByIdVideo(idSelect);
        listSelectVideo.add(video);
        System.out.println("Enter any key to continue - Enter quit to exit select Category");
        String exitSelect = Config.scanner().nextLine();
        if (exitSelect.equalsIgnoreCase("quit")) {
            return;
        } else {
            selectVideo(listSelectVideo);
        }

    }

    private void formShowListSeriesFilm() {
        System.out.println("*********************LIST SERIES FILM*********************");
        for (Series series: seriesList) {
            System.out.println(series);
        }
    }

    private void formCreateSeriesFilm() {
        int lastId;
        if (videoList.isEmpty()) {
            lastId = 1;
        } else {
            lastId = videoList.get(videoList.size() - 1).getId() + 1;
        }
        System.out.println("Enter name Series Film");
        String name = "";
        while (!name.matches("(^[A-Za-z]{1,16})([ ]{0,1})([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})")) {
            name  = Config.scanner().nextLine();
            if (!name.matches("(^[A-Za-z]{1,16})([ ]{0,1})([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})")) {
                System.out.println("Invalid name");
            }
        }
        System.out.println("Add video");

        List<Video> listSelectVideo = new ArrayList<>();
        selectVideo(listSelectVideo);
        Series series = new Series(lastId, name,listSelectVideo);
        System.out.println("Create Success!!!!!!!");
        seriesFilmController.createSeries(series);
        seriesFilmController.getListSeries();

    }
}
