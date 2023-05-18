package ru.delivery.fastdelivery.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
	
	private Long order_id;	
	private Float weight;	
	private Integer regions;
	private String[] delivery_hours;	
	private Integer cost;	
	private LocalDateTime completed_time;	
	
}
