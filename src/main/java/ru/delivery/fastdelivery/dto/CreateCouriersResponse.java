package ru.delivery.fastdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCouriersResponse {
	private CourierDto[] couriers;
}
