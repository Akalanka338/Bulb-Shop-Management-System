package lk.ijse.gdse.entity;

public class Category implements SuperEntity{

    private String catid;
    private String name;

    public Category() {
    }

    public Category(String catid, String name) {
        this.catid = catid;
        this.name = name;
    }

    public Category(String catid) {
        this.catid = catid;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catid='" + catid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
