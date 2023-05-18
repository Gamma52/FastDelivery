package ru.delivery.fastdelivery.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.delivery.fastdelivery.enums.Courier_type;

@Data
@NoArgsConstructor
public class GetCourierMetaInfoResponse {
	private Long courier_id;
	private Courier_type courier_type;
	private Integer[] regions;
	private String[] working_hours;
	private Integer rating;
	private Integer earnings;	
}
