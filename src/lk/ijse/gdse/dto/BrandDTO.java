package lk.ijse.gdse.dto;

public class BrandDTO {

    private String bid;
    private String name;

    public BrandDTO() {
    }

    public BrandDTO(String bid, String name) {
        this.bid = bid;
        this.name = name;
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
        return "BrandDTO{" +
                "bid='" + bid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
