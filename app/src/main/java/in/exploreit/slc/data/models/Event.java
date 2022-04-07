package in.exploreit.slc.data.models;

import java.util.Date;

public class Event implements ListItem{
    public String name;
    public String img;
    public String desc;
    public Date date_time;
    public Boolean paid;
    public int price;
    public int hours;
    public String target_url;
    public String venue;

    public int getPrice() { return price; }

    public Date getDate_time() {return date_time;}

    public String getVenue() { return venue; }

    @Override public String getTitle() { return name; }

    @Override public String getDescription() { return desc; }

    @Override public int getTimeStamp() { return hours; }

    @Override public String getImageUrl() { return img; }

    @Override public int getNumberOfDescLines() { return 2; }

    @Override public String getTargetUrl() { return target_url; }
}
