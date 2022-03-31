package in.exploreit.slc.data.models;

import java.util.Date;

public class Event implements ListItem{
    private String name;
    private String img;
    private String desc;
    private Date date_time;
    private Boolean paid;
    private int price;
    private int hours;
    private String venue;

    public Event(){
//            public exercises(String name, String time_taken, String image){
//            this.name = name;
//            this.time_taken = time_taken;
//            this.image= image;
    }

    public Event(String title, String desc, String img, int hours) {
        this.name = title;
        this.desc = desc;
        this.img = img;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int time) {
        this.hours = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override public String getTitle() { return name; }

    @Override public String getDescription() { return desc; }

    @Override public int getTimeStamp() { return hours; }

    @Override public String getImageUrl() { return img; }

    @Override public int getNumberOfDescLines() { return 2; }

    @Override public String getTargetUrl() { return venue; }
}
