package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="bank_accounts")
public class BanckAcc extends BillingDetail {

    private String name;
    private String swift;

    public BanckAcc() {
    }
   @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   @Column
    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }
}
