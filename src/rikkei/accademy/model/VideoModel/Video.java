package rikkei.accademy.model.VideoModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Video implements Serializable{
    static final long serialVersionUID = 1L;
    private int id;
    private String nameVideo;
    private long view;
    private Date date;
    private List<Category> categories;

    public Video() {
    }

    public Video(int id, String nameVideo, long view, Date date, List<Category> categories) {
        this.id = id;
        this.nameVideo = nameVideo;
        this.view = view;
        this.date = date;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", nameVideo='" + nameVideo + '\'' +
                ", view=" + view +
                ", date=" + date +
                ", categories=" + categories +
                '}';
    }
}
