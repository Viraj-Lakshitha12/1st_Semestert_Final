package lk.ijse.phone.util;

public class GetIncomeTotal {
    private static double income;
    private static double total;

    public GetIncomeTotal() {
    }

    public GetIncomeTotal(double income) {
     total = this.income +=income;
    }

    public double getIncome() {
        return total ;
    }

    public void setIncome(double income) {
       total =  this.income = income;
    }


}
