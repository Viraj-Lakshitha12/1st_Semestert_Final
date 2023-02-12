package lk.ijse.phone.util;

public class GetCusId {
    public GetCusId(String cusId){
        this.setCusId(cusId);
    }

    private static String cusId;

    public  String getCusId() {
        return cusId;
    }

    public  GetCusId() {
    }
    public  void setCusId(String cusId) {
        GetCusId.cusId = cusId;
    }
}