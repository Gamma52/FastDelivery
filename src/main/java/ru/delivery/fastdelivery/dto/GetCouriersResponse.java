package ru.delivery.fastdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCouriersResponse {
	private CourierDto[] couriers;
	private Integer limit;
	private Integer offset;
}
