package ru.delivery.fastdelivery.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import ru.delivery.fastdelivery.model.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long> {
	
	@Transactional//(readOnly = true)
    @Query("select r from Rating r where r.courier=:id")
    public List<Rating> findRatingT(@Param("id") Long id);
	
	@Transactional//(readOnly = true)
    @Query("select r from Rating r where r.courier.courier_id=:id and r.order.completed_time>=:start_date and r.order.completed_time<=:end_date")
    public List<Rating> findRatingCourier(@Param("id") Long id, @Param("start_date") LocalDateTime start_date, @Param("end_date") LocalDateTime end_date);

}
