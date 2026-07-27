package ng.myChemist.dto.request;

import java.time.LocalDate;
import java.time.YearMonth;

public class AddBatchRequest {
    private String drugId;
    private String brand;
    private YearMonth expiryDate;
    private YearMonth purchasedDate;
    private double price;
    private int quantity;

    public String getDrugId(){
        return drugId;
    }
    public void setDrugId(String drugId){
        this.drugId = drugId;
    }

    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    public YearMonth getExpiryDate(){
        return expiryDate;
    }
    public void setExpiryDate(YearMonth expiryDate){
        this.expiryDate = expiryDate;
    }

    public YearMonth getPurchasedDate(){
        return purchasedDate;
    }
    public void setPurchasedDate(YearMonth purchasedDate){
        this.purchasedDate = purchasedDate;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }


    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
