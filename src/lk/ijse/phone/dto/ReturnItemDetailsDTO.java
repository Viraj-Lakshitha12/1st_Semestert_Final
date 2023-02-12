package lk.ijse.phone.dto;

import java.util.ArrayList;

public class ReturnItemDetailsDTO {
    private String RorderId;
    private String date;
    private String time;

    public ReturnItemDetailsDTO(String rorderId, String date, String time, ArrayList<ReturnOrderDTO> arrayList) {
        setRorderId(rorderId);
        this.setDate(date);
        this.setTime(time);
        this.setArrayList(arrayList);
    }

    public ReturnItemDetailsDTO() {
    }

    private ArrayList<ReturnOrderDTO> arrayList;

    public String getRorderId() {
        return RorderId;
    }

    public void setRorderId(String rorderId) {
        RorderId = rorderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<ReturnOrderDTO> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ReturnOrderDTO> arrayList) {
        this.arrayList = arrayList;
    }
}
