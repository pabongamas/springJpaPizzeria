package com.platzi.platzipizzeria.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id_order",nullable = false)
    private Long idOrder;

    @Column(name="id_customer",nullable = false,length = 15)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "timestamp")	// para mysql es "datetime"
    private LocalDateTime date;

    @Column(nullable = false,columnDefinition = "Decimal(6,2)")
    private Double total;

    @Column(nullable = false,columnDefinition = "CHAR(1)")
    private String method;

    @Column(name = "additional_notes",length = 200)
    private String additionalNotes;

    // LAZY SIGNIFICA NO CARGA ESTA RELACION SINO HASTA QUE SE USA
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer",referencedColumnName = "id_customer",insertable = false,updatable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy ="order",fetch = FetchType.EAGER)
    private List<OrderItemEntity> items;

    
}
