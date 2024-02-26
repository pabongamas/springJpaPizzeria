package com.platzi.platzipizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.persistence.repository.PizzaPagSortRepository;
import com.platzi.platzipizzeria.persistence.repository.PizzaRepository;


@Service
public class PizzaService {
    

    // private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    // @Autowired
    // public PizzaService(JdbcTemplate jdbcTemplate) {
    // this.jdbcTemplate = jdbcTemplate;
    // }

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository,PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository=pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page,int elements) {
        // return this.jdbcTemplate.query("SELECT * FROM pizza where available='f'", new
        // BeanPropertyRowMapper<>(PizzaEntity.class));
        // return this.pizzaRepository.findAll();


        //esto nuevo es para paginar 
        Pageable pageRequest=PageRequest.of(page,elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }
    // public List<PizzaEntity> getAvailable(){
    //     Integer pizzasVegans=this.pizzaRepository.countByVeganTrue();
    //     System.out.println(pizzasVegans);
    //     return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    // }
      public Page<PizzaEntity> getAvailable(int page,int elements,String sortBy,String sortDirection){

        Sort sort=Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest=PageRequest.of(page,elements,sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }
    public List<PizzaEntity> getWhit(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }
    public List<PizzaEntity> getWhitOut(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity get(Long idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(()->new RuntimeException("La pizza no existe"));
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
