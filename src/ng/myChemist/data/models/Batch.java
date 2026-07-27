package ng.myChemist.data.models;

import java.time.YearMonth;


public class Batch {

    private String id;
    private Drug drug;
    private String brand;
    private YearMonth expiryDate;
    private YearMonth purchasedDate;
    private double price;
    private int quantity;

public String getId(){
    return id;
}
public void setId(String id){
    this.id = id;
}

public Drug getDrug(){
    return drug;
}
public void setDrug(Drug drug){
    this.drug = drug;
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
