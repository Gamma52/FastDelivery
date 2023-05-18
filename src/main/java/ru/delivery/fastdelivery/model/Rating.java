package ru.delivery.fastdelivery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "rating")
public class Rating {	
	
	@Id		
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "order_id")
	@MapsId
	private Order order;		

	@ManyToOne
	@JoinColumn(name = "courier", referencedColumnName = "courier_id")
	private Courier courier;
	
	@Column(name = "earnings")	
	private Integer earnings;
	
}
