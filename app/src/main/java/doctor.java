

public class doctor {
    private String type,location;
    private int docid,fees;
    public doctor(){}
    public doctor(int docid,String type,String location,int fees){
        this.docid=docid;
        this.type=type;
        this.location=location;
        this.fees=fees;
    }
    public void setid(int id){
        this.docid=id;
    }
    public int getid(){
        return this.docid;
    }
    public void settype(String type){
        this.type=type;
    }
    public String gettype(){
        return this.type;
    }
    public void setfees(int fees){
        this.fees=fees;
    }
    public int getfees(){
        return this.fees;
    }
    public void setlocation(String location){
        this.location=location;
    }
    public String getlocation(){
        return this.location;
    }

}
