package com.platzi.platzipizzeria.persistence.audit;

import org.springframework.util.SerializationUtils;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class AuditPizzaListener {

    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity entity){
        System.out.println("POST LOAD");
        // esto se hace para sereializar y como decir clonar el objeto para cuando se utilice no sea remplazado y tener el propio valor 
        this.currentValue=SerializationUtils.clone(entity);
    }
    
    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: "+this.currentValue);
        System.out.println("NEW VALUE: "+entity);
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity){
        System.out.println(entity.toString());
    }
}
