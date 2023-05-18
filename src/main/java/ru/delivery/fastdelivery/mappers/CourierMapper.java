package ru.delivery.fastdelivery.mappers;

import java.util.List;

import ru.delivery.fastdelivery.dto.CourierDto;
import ru.delivery.fastdelivery.dto.CreateCourierDto;
import ru.delivery.fastdelivery.dto.GetCouriersResponse;
import ru.delivery.fastdelivery.model.Courier;

import java.util.ArrayList;

public final class CourierMapper {
	
	
	public static CourierDto toCourierDto(Courier courier) {
		return new CourierDto(courier.getCourier_id(),
							  courier.getCourier_type(),
						   	  courier.getRegions(),
							  courier.getWorking_hours()); 
	}
	
	
	public static Courier toCourier(CreateCourierDto createCourierDto) {
		Courier courier = new Courier();
		courier.setRegions(createCourierDto.getRegions());
		courier.setCourier_type(createCourierDto.getCourier_type());
		courier.setWorking_hours(createCourierDto.getWorking_hours());
		return courier; 
	}
	
	
	public static GetCouriersResponse toGetCouriersResponse(CourierDto[] courierDtoArray, Integer limit, Integer offset) {
		return new GetCouriersResponse(courierDtoArray, limit, offset); 
	}
	
	
	public static List<CourierDto> toListCourierDto(List<Courier> courierList) {
		List<CourierDto> courierDtoList = new ArrayList<CourierDto>();
		for(Courier courier:courierList) {
			courierDtoList.add(toCourierDto(courier));
		}
		return courierDtoList;
	}
	
	public static List<Courier> toListCourier(List<CreateCourierDto> courierDtoList) {
		List<Courier> courierList = new ArrayList<Courier>();
		for(CreateCourierDto createCourierDto:courierDtoList) {
			courierList.add(toCourier(createCourierDto));
		}
		return courierList;
	}
	
}
