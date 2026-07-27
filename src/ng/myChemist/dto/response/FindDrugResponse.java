package ng.myChemist.dto.response;

public class FindDrugResponse {
    private String id;
    private String drugName;


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
}

