package lk.ijse.gdse.entity;

public class OrderDetail implements SuperEntity {
    private String Did;
    private String item_code;
    private int qty;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(String did, String item_code, int qty, double unitPrice) {
        Did = did;
        this.item_code = item_code;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDetail(String did) {
        Did = did;
    }

    public String getDid() {
        return Did;
    }

    public void setDid(String did) {
        Did = did;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "Did='" + Did + '\'' +
                ", item_code='" + item_code + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
