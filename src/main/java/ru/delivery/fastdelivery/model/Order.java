package ru.delivery.fastdelivery.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")	
	private Long order_id;
	
	@Column(name = "weight", nullable = false)
	private Float weight;
	
	@Column(name = "regions", nullable = false)
	private Integer regions;
	
	@Column(name = "delivery_hours", nullable = false)
	private String[] delivery_hours;
	
	@Column(name = "cost", nullable = false)
	private Integer cost;
	
	@Column(name = "completed_time", nullable = true)
	private LocalDateTime completed_time;
}
