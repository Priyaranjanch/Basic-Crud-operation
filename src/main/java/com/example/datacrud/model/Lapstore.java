package com.example.datacrud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
@Entity
@Table(name="details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lapstore {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long price;

    private String lapname;

    private int quantity;
}
