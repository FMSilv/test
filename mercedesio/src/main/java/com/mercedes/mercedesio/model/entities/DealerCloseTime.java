package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "DEALER_CLOSETIME")
public class DealerCloseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String dealerId;
    private Date day;

}
