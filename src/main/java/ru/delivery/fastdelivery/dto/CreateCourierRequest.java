package ru.delivery.fastdelivery.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCourierRequest {
 private CreateCourierDto[] couriers;
}
