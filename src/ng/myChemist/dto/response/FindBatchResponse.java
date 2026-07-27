package ng.myChemist.dto.response;

import java.time.YearMonth;

public class FindBatchResponse {
    private String id;
    private String drugName;
    private String brand;
    private YearMonth expiryDate;
    private YearMonth purchasedDate;
    private double price;
    private int quantity;

    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public YearMonth getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(YearMonth expiryDate) {
        this.expiryDate = expiryDate;
    }

    public YearMonth getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(YearMonth purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

