package com.platzi.platzipizzeria.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.service.dto.UpdatePizzaPriceDto;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    // esto me retorna un registro
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    Integer countByVeganTrue();

    // la actualizacion se puede hacer asi
    // @Query(value = "UPDATE pizza set price=:newPrice WHERE
    // id_pizza=:idPizza",nativeQuery =true)
    // void updatePrice(@Param("idPizza") int idPizza,@Param("newPrice") double
    // newPrice);

    // o asi
    @Query(value = "UPDATE pizza set price=:#{#newPizzaPrice.newPrice} WHERE id_pizza=:#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);

}
