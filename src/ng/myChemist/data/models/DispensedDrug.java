package ng.myChemist.data.models;


public class DispensedDrug {
    private String id;
    private Batch batch;
    private double price;
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public Batch getBatch(){
        return batch;
    }
    public void setBatch(Batch batch){
        this.batch = batch;
    }

    public double getPrice(){

        return price;
    }
    public void setPrice(double price){
        this.price = price;}

    public int getQuantity(){

        return quantity;
    }
    public void setQuantity(int quantity){

        this.quantity = quantity;
    }

}