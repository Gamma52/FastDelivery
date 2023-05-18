package ru.delivery.fastdelivery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.delivery.fastdelivery.dto.CourierDto;
import ru.delivery.fastdelivery.dto.CreateCourierRequest;
import ru.delivery.fastdelivery.dto.CreateCouriersResponse;
import ru.delivery.fastdelivery.dto.GetCouriersResponse;
import ru.delivery.fastdelivery.exceptions.BadRequestException;
import ru.delivery.fastdelivery.service.CourierService;



@RestController
@RequestMapping("/couriers")
public class CourierController {
	
	@Autowired
	private CourierService courierService;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public GetCouriersResponse showCouriers(@RequestParam(name = "limit", defaultValue = "10") Integer limit,
											@RequestParam(name = "offset", defaultValue = "0") Integer offset) throws BadRequestException {        
	return courierService.getAllCouriers(limit, offset);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
    public CreateCouriersResponse saveCouriers(@RequestBody CreateCourierRequest createCourierRequest){
		CreateCouriersResponse createCouriersResponse = courierService.addListCouriers(createCourierRequest);		
        return createCouriersResponse;
    }
	
	
	@GetMapping("/{courier_id}")
	@ResponseStatus(HttpStatus.OK)
	public CourierDto findCourierById(@PathVariable("courier_id") long courier_id){		
		return courierService.getCourierById(courier_id); 
	}
	
	
}
