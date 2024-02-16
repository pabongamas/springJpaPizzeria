package com.platzi.platzipizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.persistence.repository.PizzaRepository;

@Service
public class PizzaService {

    // private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository pizzaRepository;

    // @Autowired
    // public PizzaService(JdbcTemplate jdbcTemplate) {
    // this.jdbcTemplate = jdbcTemplate;
    // }

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        // return this.jdbcTemplate.query("SELECT * FROM pizza where available='f'", new
        // BeanPropertyRowMapper<>(PizzaEntity.class));
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity get(Long idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return this.pizzaRepository.save(pizzaEntity);
    }
    public boolean exists(Long idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
    public void delete(Long idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

}
