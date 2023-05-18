package ru.delivery.fastdelivery.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.delivery.fastdelivery.dto.CourierDto;
import ru.delivery.fastdelivery.dto.CreateCourierDto;
import ru.delivery.fastdelivery.dto.CreateCourierRequest;
import ru.delivery.fastdelivery.dto.CreateCouriersResponse;
import ru.delivery.fastdelivery.dto.GetCouriersResponse;
import ru.delivery.fastdelivery.exceptions.BadRequestException;
import ru.delivery.fastdelivery.exceptions.NotFoundException;
import ru.delivery.fastdelivery.mappers.CourierMapper;
import ru.delivery.fastdelivery.model.Courier;
import ru.delivery.fastdelivery.repositories.CourierRepository;


@Service
public class CourierService {

	@Autowired
	private CourierRepository courierRepository;
	
	
	public CreateCouriersResponse addListCouriers(CreateCourierRequest createCourierRequest) {
		List<CreateCourierDto> createCourierDtoList;
		List<Courier> courierList;
		try {
			createCourierDtoList = Arrays.asList(createCourierRequest.getCouriers());
			courierList = (List<Courier>) courierRepository.saveAll(CourierMapper.toListCourier(createCourierDtoList));
		} catch(RuntimeException e) {
			throw new BadRequestException("JSON Error");
		}		 
		
		List<CourierDto> courierDtoList = CourierMapper.toListCourierDto(courierList);
		
		CourierDto[] courierDtoArray = new CourierDto[courierDtoList.size()];
		courierDtoArray = courierDtoList.toArray(courierDtoArray);	
		
		return new CreateCouriersResponse(courierDtoArray);
	}
	
	
	public GetCouriersResponse getAllCouriers(Integer limit, Integer offset) {
		List<Courier> courierList = (List<Courier>) courierRepository.findAll();
		List<CourierDto> courierDtoList = CourierMapper.toListCourierDto(courierList);
		
		if (offset < 0 || limit <= offset) {
			throw new BadRequestException("limit or offset is wrong: BadRequestException");
		}
		
		if (courierDtoList.size() <= offset) {
			courierDtoList.clear();
		}else {
			if (courierDtoList.size() >= limit+offset) {
				courierDtoList = courierDtoList.subList(offset, limit+offset);
			}else {
				courierDtoList = courierDtoList.subList(offset, courierDtoList.size());
			}
		}
		CourierDto[] courierDtoArray = new CourierDto[courierDtoList.size()];
		courierDtoArray = courierDtoList.toArray(courierDtoArray);		
		
		return new GetCouriersResponse(courierDtoArray, limit, offset);
	}	
	
	
	public CourierDto getCourierById(Long courier_id) {
		Courier courier = courierRepository.findById(courier_id)
						.orElseThrow(() -> new NotFoundException("Courier not found"));
		return CourierMapper.toCourierDto(courier);		
	}
	
}
