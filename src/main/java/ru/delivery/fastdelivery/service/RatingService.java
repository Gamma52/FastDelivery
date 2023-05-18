package ru.delivery.fastdelivery.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.delivery.fastdelivery.dto.GetCourierMetaInfoResponse;
import ru.delivery.fastdelivery.exceptions.NotFoundException;
import ru.delivery.fastdelivery.model.Rating;
import ru.delivery.fastdelivery.repositories.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepo;
	
	
	public GetCourierMetaInfoResponse showRatingCourier(Long courier_id, LocalDateTime start_date, LocalDateTime end_date) {
		GetCourierMetaInfoResponse metaInfoResponse = new GetCourierMetaInfoResponse();		
		List<Rating> ratings = (List<Rating>) ratingRepo.findRatingCourier(courier_id, start_date, end_date);
		metaInfoResponse.setCourier_id(courier_id);
		
		if(ratings.isEmpty()) {
			return metaInfoResponse;
		}
		
		metaInfoResponse.setCourier_type(ratings.get(0).getCourier().getCourier_type());
		metaInfoResponse.setRegions(ratings.get(0).getCourier().getRegions());
		metaInfoResponse.setWorking_hours(ratings.get(0).getCourier().getWorking_hours());
		
		int sumEarnings = 0;		
		Long duration = Duration.between(start_date, end_date).toHours();
		int koef;
		
		switch(ratings.get(0).getCourier().getCourier_type()){
		case FOOT:
            koef = 3;
            break;
		case BIKE:
			koef = 2;
            break;
        case AUTO:
        	 koef = 1;
            break;
        default:
            throw new NotFoundException("Unknown Type of Courier whith id:"+courier_id);
		}
		
		for(Rating item:ratings) {
			sumEarnings += item.getEarnings();			
		}
		
		Long netRating = ratings.size() * koef / duration;
		
		metaInfoResponse.setEarnings(sumEarnings);
		metaInfoResponse.setRating(netRating.intValue());		
		
		//ratings = (List<Rating>) ratingRepo.findRatingCourier(start_date, end_date);
		
		return metaInfoResponse;
	}	

}