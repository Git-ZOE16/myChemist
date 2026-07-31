package ng.myChemist.dto.request;

public class DispenseDrugRequest {

    private String batchId;
    private int quantity;

    public String getBatchId(){
        return batchId;
    }
    public void setBatchId(String batchId){
        this.batchId = batchId;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
