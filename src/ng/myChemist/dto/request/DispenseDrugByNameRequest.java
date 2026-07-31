package ng.myChemist.dto.request;

public class DispenseDrugByNameRequest {

    private String drugId;
    private int quantity;

    public String getDrugId(){
        return drugId;
    }
    public void setDrugId(String drugId){
        this.drugId = drugId;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
