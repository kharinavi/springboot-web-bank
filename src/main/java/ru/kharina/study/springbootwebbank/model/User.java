package ru.kharina.study.springbootwebbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=30, message="Name should be between 2 and 30 characters" )
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "summ")
    private int summ;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Transaction> sendTransactionList;

    @OneToMany(mappedBy = "receipient", cascade = CascadeType.ALL)
    private List<Transaction> getTransactionList;
}
