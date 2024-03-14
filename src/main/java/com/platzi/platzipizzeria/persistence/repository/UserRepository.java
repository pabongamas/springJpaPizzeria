package com.platzi.platzipizzeria.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.platzi.platzipizzeria.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,String> {
    
}
