package ru.delivery.fastdelivery.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompleteOrderRequestDto {
	private CompleteOrder[] complete_info;
}
