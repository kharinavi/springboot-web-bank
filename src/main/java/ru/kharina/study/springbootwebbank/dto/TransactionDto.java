package ru.kharina.study.springbootwebbank.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
public class TransactionDto {
    private int id;
    private double summ;
    private Date date;
    @Min(1)
    private int sender_id;
    @Min(1)
    private int receipient_id;
    private String message;
}
