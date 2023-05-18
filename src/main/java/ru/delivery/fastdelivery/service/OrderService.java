package ru.delivery.fastdelivery.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.delivery.fastdelivery.dto.CompleteOrder;
import ru.delivery.fastdelivery.dto.CompleteOrderRequestDto;
import ru.delivery.fastdelivery.dto.CreateOrderRequest;
import ru.delivery.fastdelivery.dto.OrderDto;
import ru.delivery.fastdelivery.exceptions.BadRequestException;
import ru.delivery.fastdelivery.exceptions.NotFoundException;
import ru.delivery.fastdelivery.mappers.OrderMapper;
import ru.delivery.fastdelivery.model.Courier;
import ru.delivery.fastdelivery.model.Order;
import ru.delivery.fastdelivery.model.Rating;
import ru.delivery.fastdelivery.repositories.CourierRepository;
import ru.delivery.fastdelivery.repositories.OrderRepository;
import ru.delivery.fastdelivery.repositories.RatingRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private CourierRepository courierRepository;
	
	
	public List<OrderDto> addListOrder(CreateOrderRequest createOrderRequest) {	
		List<Order> orderList;
		try {
			orderList = (List<Order>) orderRepository.saveAll(OrderMapper.toListOrder(createOrderRequest));
		} catch(RuntimeException e) {
			throw new BadRequestException("JSON Error");
		}		 
		return OrderMapper.toListOrderDto(orderList);
	}
	
	
	public List<OrderDto> getAllOrders(Integer limit, Integer offset) {
		List<Order> orderList = (List<Order>) orderRepository.findAll();
		List<OrderDto> orderDtoList = orderList.stream()
											.map((o1 -> OrderMapper.toOrderDto(o1))).collect(Collectors.toList());
		
		if (offset < 0 || limit <= offset) {
			throw new BadRequestException("limit or offset is wrong: BadRequestException");
		}
		
		if (orderDtoList.size() <= offset) {
			return new ArrayList<OrderDto>();
		}
		if (orderDtoList.size() >= limit+offset) {
			orderDtoList = orderDtoList.subList(offset, limit+offset);
		}else {
			orderDtoList = orderDtoList.subList(offset, orderDtoList.size());
		}
		
		return orderDtoList;
	}
	
	
	public OrderDto getCourierById(Long order_id) {
		Order order = orderRepository.findById(order_id)
						.orElseThrow(() -> new NotFoundException("Order not found"));
		return OrderMapper.toOrderDto(order);		
	}
	
	
	public List<OrderDto> updateListOrder(CompleteOrderRequestDto completeOrderRequestDto) {	
		CompleteOrder[] completeOrder = completeOrderRequestDto.getComplete_info();
		addListRating(completeOrder);
		List<Order> orderList = new ArrayList<>();
		for(CompleteOrder item:completeOrder) {
			Order order = orderRepository.findById(item.getOrder_id())
					.orElseThrow(() -> new NotFoundException("Order whith id:"+item.getOrder_id()+" not found"));				
			order.setCompleted_time(item.getComplete_time());
			orderList.add(order);
		}
		
		List<Order> updOrderList = (List<Order>) orderRepository.saveAll(orderList);	
		return OrderMapper.toListOrderDto(updOrderList);
	}
	
	
	private void addListRating(CompleteOrder[] completeOrder) {
		List<Rating> ratingList = new ArrayList<>();
		for(CompleteOrder item:completeOrder) {
			Rating rating = new Rating();
			Order order = orderRepository.findById(item.getOrder_id())
						.orElseThrow(() -> new NotFoundException("Order whith id:"+item.getOrder_id()+" not found"));
			Courier courier = courierRepository.findById(item.getCourier_id())
						.orElseThrow(() -> new NotFoundException("Courier whith id:"+item.getCourier_id()+" not found"));			
			rating.setOrder(order);
			rating.setCourier(courier);
			
			//Earnings formula
			int cost = order.getCost();
			int koef;
			switch(courier.getCourier_type()){
				case FOOT:
		            koef = 2;
		            break;
				case BIKE:
					koef = 3;
		            break;
		        case AUTO:
		        	 koef = 4;
		            break;
		        default:
		            throw new NotFoundException("Unknown Type of Courier whith id:"+item.getCourier_id());
			}
			int earnings = cost * koef;			
			
			rating.setEarnings(earnings);
			ratingList.add(rating);			
		}
		
		try {
			ratingRepository.saveAll(ratingList);	
		} catch(RuntimeException e) {
			throw new BadRequestException("Duplicate key value");
		}
	}
	
}
