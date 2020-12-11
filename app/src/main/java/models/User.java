package models;

import org.parceler.Parcel;

@Parcel
public class User {
    private String tenantName;
    private String email;
    private String apartmentNumber;


    public User(){}

    public User(String tenantName,String email,String apartmentNumber){
        this.tenantName=tenantName;
        this.email=email;
        this.apartmentNumber=apartmentNumber;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
