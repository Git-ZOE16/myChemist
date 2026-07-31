package ng.myChemist.dto.response;

public class FindDispensedDrugResponse {

    private String id;
    private String drugName;
    private int quantity;
    private double price;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getDrugName(){
        return drugName;
    }
    public void setDrugName(String drugName){
        this.drugName = drugName;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
}
