package ru.delivery.fastdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.delivery.fastdelivery.enums.Courier_type;

@Data
@AllArgsConstructor
public class CourierDto {
	private Long courier_id;
	private Courier_type courier_type;
	private Integer[] regions;
	private String[] working_hours;
}
