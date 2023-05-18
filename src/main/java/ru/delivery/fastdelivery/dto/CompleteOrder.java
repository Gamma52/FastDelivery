package ru.delivery.fastdelivery.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompleteOrder {
	private Long courier_id;
	private Long order_id;
	private LocalDateTime complete_time;
}
