package com.mercedes.mercedesio.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEALERS_CLOSETIME")
public class DealerCloseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private long id;
    @Column(name="DAY", nullable = false)
    private String day;

    //bi-directional many-to-one association to DEALER
    @ManyToOne
    @JoinColumn(name="DEALER_ID", referencedColumnName = "ID", nullable = false)
    @JsonBackReference
    private Dealer dealer;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
