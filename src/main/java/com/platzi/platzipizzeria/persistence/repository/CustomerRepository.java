package com.platzi.platzipizzeria.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.platzi.platzipizzeria.persistence.entity.CustomerEntity;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity,String> {

    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber=:phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
    
}
