package lk.ijse.phone.util;

public class GetOrderId {
    private static String  orderId;

    public GetOrderId() {
    }

    public GetOrderId(String orderId) {
        this.setOrderId(orderId);
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
