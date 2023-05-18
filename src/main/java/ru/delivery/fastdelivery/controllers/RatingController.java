package ru.delivery.fastdelivery.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.delivery.fastdelivery.dto.GetCourierMetaInfoResponse;
import ru.delivery.fastdelivery.exceptions.BadRequestException;
import ru.delivery.fastdelivery.service.RatingService;

@RestController
public class RatingController {
	
	@Autowired
	private RatingService ratingService;	
	
	
	@GetMapping("/couriers/meta-info/{courier_id}")
	@ResponseStatus(HttpStatus.OK) // GetCourierMetaInfoResponse
    public GetCourierMetaInfoResponse showCuroRate(@PathVariable("courier_id") long courier_id,		
					    						   @RequestParam(name = "startDate", defaultValue = "null") String startDate_str,
					    						   @RequestParam(name = "endDate", defaultValue = "null") String endDate_str){		

		LocalDateTime startDate;
		LocalDateTime endDate;
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;		
			startDate = LocalDate.parse(startDate_str, formatter).atStartOfDay();
			endDate = LocalDate.parse(endDate_str, formatter).atStartOfDay();
		} catch (RuntimeException e) {
			throw new BadRequestException("BadRequest");
		}
		
		return ratingService.showRatingCourier(courier_id, startDate, endDate);	
    }

}
