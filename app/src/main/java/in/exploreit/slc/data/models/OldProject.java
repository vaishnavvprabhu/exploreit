package in.exploreit.slc.data.models;

public class OldProject implements ListItem{
    private String name;
    private String img;
    private String desc;
    private int year;
    private String link;

    public OldProject(){

/*            public exercises(String name, String time_taken, String image){
            this.name = name;
            this.time_taken = time_taken;
            this.image= image;*/
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override public String getTitle() { return name; }

    @Override public String getDescription() { return desc; }

    @Override public int getTimeStamp() { return year; }

    @Override public String getImageUrl() { return img; }

    @Override public int getNumberOfDescLines() { return 3; }

    @Override public String getTargetUrl() { return link; }
}
