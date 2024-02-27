package com.platzi.platzipizzeria.persistence.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.platzi.platzipizzeria.persistence.audit.AuditPizzaListener;
import com.platzi.platzipizzeria.persistence.audit.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name="pizza")
//para el caso de auditorias , y si agrego un listener de audit personalizado lo agrego en llaves asi 
@EntityListeners({AuditingEntityListener.class,AuditPizzaListener.class})
@Getter
@Setter
@NoArgsConstructor
// se coloca serializablr ya que se necesita serializar los datos en el AuditPizzaListener
public class PizzaEntity extends AuditableEntity implements Serializable{
    
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

    @Override
    public String toString() {
        return "PizzaEntity [idPizza=" + idPizza + ", name=" + name + ", description=" + description + ", price="
                + price + ", vegetarian=" + vegetarian + ", vegan=" + vegan + ", available=" + available + "]";
    }

    


}
