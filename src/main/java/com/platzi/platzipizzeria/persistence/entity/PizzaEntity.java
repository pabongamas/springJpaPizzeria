package com.platzi.platzipizzeria.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza",nullable = false)
    private Long idPizza;
    
    @Column(nullable = false,length = 30,unique = true)
    private String name;

    @Column(nullable = false,length = 150)
    private String description;

    @Column(nullable = false,columnDefinition ="Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "smallint")
    private Boolean vegetarian;

    @Column(columnDefinition = "smallint")
    private Boolean vegan;

    @Column(columnDefinition="smallint",nullable = false)
    private Boolean available;


}
