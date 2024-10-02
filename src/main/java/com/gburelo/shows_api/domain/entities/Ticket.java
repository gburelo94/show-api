package com.gburelo.shows_api.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "show_id")
    private Integer showId;

    @Column(precision = 2)
    private Double price;

    @Column(name = "redemption_code", unique = true)
    private String redemptionCode;

    @Column
    private String status;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
