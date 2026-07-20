package ng.myChemist.data.models;

import java.time.LocalDate;

public class Drug {
    private String id;
    private String name;
    private String brand;
    private LocalDate expiryDate;
    private int price;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this. name = name;
    }

    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    public LocalDate getExpiryDate(){
        return expiryDate;
    }
    public LocalDate setExpiryDate(LocalDate expiryDate){
        this.expiryDate = expiryDate;
        return expiryDate;
    }

    public int getPrice(){
        return price;
    }
    public void setPrice(int prize){
        this.price = prize;
    }
}