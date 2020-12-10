package models;

import org.parceler.Parcel;

@Parcel
public class Payment {
    private String apartmentNo;
    private String month;
    private String transactionCode;
    private Integer amount;

    public Payment(){

    }

    public Payment(String apartmentNo,String month,String transactionCode,Integer amount){
        this.apartmentNo=apartmentNo;
        this.month=month;
        this.transactionCode=transactionCode;
        this.amount=amount;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
