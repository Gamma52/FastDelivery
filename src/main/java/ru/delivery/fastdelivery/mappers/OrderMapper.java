package ru.delivery.fastdelivery.mappers;

import java.util.ArrayList;
import java.util.List;

import ru.delivery.fastdelivery.dto.CreateOrderDto;
import ru.delivery.fastdelivery.dto.CreateOrderRequest;
import ru.delivery.fastdelivery.dto.OrderDto;
import ru.delivery.fastdelivery.model.Order;

public final class OrderMapper {
	
	public static OrderDto toOrderDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setOrder_id(order.getOrder_id());
		orderDto.setWeight(order.getWeight());
		orderDto.setRegions(order.getRegions());
		orderDto.setDelivery_hours(order.getDelivery_hours());
		orderDto.setCost(order.getCost());
		orderDto.setCompleted_time(order.getCompleted_time());		
        return orderDto;
    }
	
	
	public static List<OrderDto> toListOrderDto(List<Order> orderList) {
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
    	for(Order item:orderList){
    		orderDtoList.add(toOrderDto(item));
    	}
    	return orderDtoList;
    }

	
    private static Order toOrder(CreateOrderDto createOrderDto) {    	
    	Order order = new Order();
    	order.setWeight(createOrderDto.getWeight());
    	order.setRegions(createOrderDto.getRegions());
    	order.setDelivery_hours(createOrderDto.getDelivery_hours());
    	order.setCost(createOrderDto.getCost());
    	return order;
    }

    
    public static List<Order> toListOrder(CreateOrderRequest createOrderRequest) {
    	CreateOrderDto[] createOrderDto = createOrderRequest.getOrders();
    	List<Order> orderList = new ArrayList<Order>();
    	for(CreateOrderDto item:createOrderDto){
    		orderList.add(toOrder(item));
    	}
    	return orderList;
    }

    
}
