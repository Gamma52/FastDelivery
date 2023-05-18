package ru.delivery.fastdelivery.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderDto {	
	
	private Float weight;	
	private Integer regions;
	private String[] delivery_hours;	
	private Integer cost;
	
}
