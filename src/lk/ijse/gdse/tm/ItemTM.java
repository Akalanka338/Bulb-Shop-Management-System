package lk.ijse.gdse.tm;

public class ItemTM {
    private String code;
    private String name;
    private String brand;
    private String catogory;
    private Double purchase_price;
    private Double selling_price;
    private Double profit;
    private String model_number;
    private int Qty;

    public ItemTM() {
    }

    public ItemTM(String code, String name, String brand, String catogory, Double purchase_price, Double selling_price, Double profit, String model_number, int qty) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.catogory = catogory;
        this.purchase_price = purchase_price;
        this.selling_price = selling_price;
        this.profit = profit;
        this.model_number = model_number;
        Qty = qty;
    }

    public ItemTM(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCatogory() {
        return catogory;
    }

    public void setCatogory(String catogory) {
        this.catogory = catogory;
    }

    public Double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(Double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public Double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(Double selling_price) {
        this.selling_price = selling_price;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getModel_number() {
        return model_number;
    }

    public void setModel_number(String model_number) {
        this.model_number = model_number;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", catogory='" + catogory + '\'' +
                ", purchase_price=" + purchase_price +
                ", selling_price=" + selling_price +
                ", profit=" + profit +
                ", model_number='" + model_number + '\'' +
                ", Qty=" + Qty +
                '}';
    }
}
