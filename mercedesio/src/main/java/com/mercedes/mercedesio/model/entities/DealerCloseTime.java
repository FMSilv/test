package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "DEALER_CLOSETIME")
public class DealerCloseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    @Column(name="DAY")
    private String day;

    //bi-directional many-to-one association to DEALER
    @ManyToOne
    @JoinColumn(name="DEALER_ID")
    private Dealer dealer;

}
