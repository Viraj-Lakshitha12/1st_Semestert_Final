package lk.ijse.phone.entity;

public class ReturnItems {
        private String orderId;
        private String itemCode;
        private String itemName;
        private int qty;

        public ReturnItems(String orderId, String itemCode, String itemName, int qty) {
                this.orderId = orderId;
                this.itemCode = itemCode;
                this.itemName = itemName;
                this.qty = qty;
        }

        public ReturnItems() {
        }

        public String getOrderId() {
                return orderId;
        }

        public void setOrderId(String orderId) {
                this.orderId = orderId;
        }

        public String getItemCode() {
                return itemCode;
        }

        public void setItemCode(String itemCode) {
                this.itemCode = itemCode;
        }

        public String getItemName() {
                return itemName;
        }

        public void setItemName(String itemName) {
                this.itemName = itemName;
        }

        public int getQty() {
                return qty;
        }

        public void setQty(int qty) {
                this.qty = qty;
        }

        @Override
        public String toString() {
                return "ReturnItems{" +
                        "orderId='" + orderId + '\'' +
                        ", itemCode='" + itemCode + '\'' +
                        ", itemName='" + itemName + '\'' +
                        ", qty=" + qty +
                        '}';
        }
}
