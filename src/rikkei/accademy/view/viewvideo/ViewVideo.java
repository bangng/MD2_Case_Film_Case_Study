package rikkei.accademy.view.viewvideo;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.CategoryController;
import rikkei.accademy.controller.VideoController;
import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.view.viewuser.ViewHome;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ViewVideo {
    VideoController videoController = new VideoController();
    CategoryController categoryController = new CategoryController();
    List<Video> videoList = videoController.getListVideo();
    List<Category> categories = categoryController.getListCategory();

    public void menuVideo() {
        System.out.println("******************VIDEO******************");
        System.out.println("1: Show list Video");
        System.out.println("2: Create Video");
        System.out.println("3: Delete Video");
        System.out.println("4: Update video");
        System.out.println("5: Detail Video");
        System.out.println("6: Show list video with category");
        System.out.println("7: Back");

        int choice = 0;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            menuVideo();
        }
        switch (choice) {
            case 1:
                formShowListVideo();
                break;
            case 2:
                formCreateVideo();
                break;
            case 3:
                formDeleteVideo();
                break;
            case 4:
                formUpDateVideo();
                break;
            case 5:
                formDetailVideo();
                break;
            case 6:
                formVideoWithCategory();
                break;
            case 7:
                new ViewHome();
                break;

        }
        menuVideo();
    }

    private void formDetailVideo() {
        System.out.println("Enter id Video");
        int idDetail = Config.scanner().nextInt();
        if (videoController.findByIdVideo(idDetail) == null){
            System.out.println("Not Found");
        }else {
            Video video = videoController.findByIdVideo(idDetail);
            System.out.println(video);
        }
        System.out.println("Enter quit to back or another enter key to continue");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")){
            menuVideo();
        }
    }

    private void formUpDateVideo() {

        System.out.println("Enter idVideo to edit");
        int idEdit = Config.scanner().nextInt();
        if (videoController.findByIdVideo(idEdit) == null){
            System.out.println("not found");
        }else {
           Video video = videoController.findByIdVideo(idEdit);
            System.out.println("OLD videoName: " + video.getNameVideo());
            System.out.println("OLD NSX: " +video.getDateByVideo());
            System.out.println("OLD Country: "+video.getCountry());
            System.out.println("OLD Category: "+video.getCategories().stream().map(Category::getCategory).collect(Collectors.toList()));
            System.out.println("OLD isSeries: " + video.isSeriesVideo()) ;

            System.out.println("Enter new name to edit");
            String newNameVideo = Config.scanner().nextLine();
            if (!newNameVideo.trim().equals("")){
                video.setNameVideo(newNameVideo);
                return;
            }
            System.out.println("Thêm mới ngày sản xuất cho phim");
            String newNsx = Config.scanner().nextLine();
            if (!newNsx.trim().equals("")){
                video.setDateByVideo(newNsx);
                return;
            }
            LocalDate dateToUp = LocalDate.now();
            System.out.println("Enter new country");
            String newCountry = Config.scanner().nextLine();
            if (!newCountry.trim().equals("")){
                video.setCountry(newCountry);
                return;
            }
            System.out.println("Enter category want to edit");
            List<Category> listSelectCategory = new ArrayList<>();
            selectCategory(listSelectCategory);
            System.out.println("Phim bộ 1 or phim lẻ 2");
            int phim = Config.scanner().nextInt();
            boolean series = false;
            if (phim == 1) {
                series = true;
            }
            Video video1 = new Video(video.getId(),newNameVideo, 0, newNsx,dateToUp, newCountry, listSelectCategory,series);
            videoController.editVideo(video1);


            System.out.println("Success");
        }
    }

    private void formDeleteVideo() {
        System.out.println("Enter id video to delete");
        int idVideo = Config.scanner().nextInt();
        if (videoController.findByIdVideo(idVideo) == null){
            System.out.println("Not found");
        }else {
            System.out.println("Enter 1 to delete or 2 to not delete");
            int choiceDelete = Config.scanner().nextInt();
            switch (choiceDelete){
                case 1:
                   videoController.deleteVideo(idVideo);
                    formShowListVideo();
                   videoController.getListVideo();
                    break;
                case 2:
                   menuVideo();
                    break;
            }
        }
    }


    //    Chưa làm xong
    private void formVideoWithCategory() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("**" + categories.get(i).getId() + "**" + categories.get(i).getCategory());
        }
        System.out.println("Enter id category");
        int idCategory = Config.scanner().nextInt();
        videoController.showVideoWithCategory(idCategory);




        videoController.getListVideo();


    }
// chưa làm song phần trên
    private void formCreateVideo() {

        int lastId;
        if (videoList.isEmpty()) {
            lastId = 1;
        } else {
            lastId = videoList.get(videoList.size() - 1).getId() + 1;
        }
        System.out.println("Enter Video Name ");
        String videoName = "";
        while (!videoName.matches("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})")) {
            videoName = Config.scanner().nextLine();
            if (!videoName.matches("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})")) {
                System.out.println("Invalid name");
            }
        }
        System.out.println("Enter date by Video");
        String dateByVideo = "";
        while (!dateByVideo.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
            dateByVideo = Config.scanner().nextLine();
            if (!dateByVideo.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
                System.out.println("Invalid name");
            }
        }
        LocalDate dateToUp = LocalDate.now();

        System.out.println("Enter Country");
        String videoCountry = "";
        while (!videoCountry.matches("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})")) {
            videoCountry = Config.scanner().nextLine();
            if (!videoCountry.matches("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})")) {
                System.out.println("Invalid name");
            }
        }

        System.out.println("Phim bộ 1 or phim lẻ 2");
        int phim = Config.scanner().nextInt();
        boolean series = false;
        if (phim == 1) {
            series = true;
        }

        System.out.println("Add category");

        List<Category> listSelectCategory = new ArrayList<>();
        selectCategory(listSelectCategory);
        Video video = new Video(lastId, videoName, 0, dateByVideo, dateToUp, videoCountry, listSelectCategory, series);
        System.out.println("Create Success!!!!!!!");
        videoController.createVideo(video);
        videoController.getListVideo();
    }

    private void selectCategory(List<Category> listSelectCategory) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("**" + categories.get(i).getId() + "**" + categories.get(i).getCategory());
        }
        System.out.println("Enter id of category to select:");
        int idSelect = Config.scanner().nextInt();
        Category category = categoryController.detailCategory(idSelect);
        listSelectCategory.add(category);
        System.out.println("Enter any key to continue - Enter quit to exit select Category");
        String exitSelect = Config.scanner().nextLine();
        if (exitSelect.equalsIgnoreCase("quit")) {
            return;
        } else {
            selectCategory(listSelectCategory);
        }

    }

    public void formShowListVideo() {
        System.out.println("****************************LIST VIDEO*****************************");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-20s%-15s%n", "ID", "VideoName", "View", "DateByVideo", "DateToUp", "Country", "Category", "isSeries");
        for (Video video : videoList) {
            System.out.printf("%-15d%-15s%-15s%-15s%-15s%-15s%-20s%-15s%n", video.getId(), video.getNameVideo(), video.getView(), video.getDateByVideo(), video.getDateToUp(), video.getCountry(), video.getCategories().stream().map(Category::getCategory).collect(Collectors.toList()), video.isSeriesVideo() ? "Phim bộ" : "Phim Lẻ");
        }

    }
}
