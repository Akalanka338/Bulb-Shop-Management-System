package lk.ijse.gdse.entity;

public class Brand implements SuperEntity{
    private String bid;
    private String name;

    public Brand() {
    }

    public Brand(String bid, String name) {
        this.bid = bid;
        this.name = name;
    }

    public Brand(String bid) {
        this.bid = bid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "bid='" + bid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
