package com.platzi.platzipizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto { 
    private Long pizzaId;
    private double newPrice;
}
