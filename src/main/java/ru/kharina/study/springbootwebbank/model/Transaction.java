package ru.kharina.study.springbootwebbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "receipient_id")
    private User receipient;

    @Column(name = "message")
    private String message;

    @Column(name = "summ")
    private double summ;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sender_id")
    private User sender;
}
