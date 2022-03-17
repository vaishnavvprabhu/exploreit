package in.exploreit.slc.data.models;

public class new_projects {
    private String name;
    private String img;
    private String desc;
    private int year;
    private String link;

    public new_projects(){

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
}
