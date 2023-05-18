package ru.delivery.fastdelivery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.delivery.fastdelivery.enums.Courier_type;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "couriers")
public class Courier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "courier_id")	
	private Long courier_id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "courier_type", nullable = false)
	private Courier_type courier_type;
	
	@Column(name = "regions", nullable = false)
	private Integer[] regions;
	
	@Column(name = "working_hours", nullable = false)
	private String[] working_hours;	
}
