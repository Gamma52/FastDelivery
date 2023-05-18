package ru.delivery.fastdelivery.controllers;

import java.util.List;

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

import ru.delivery.fastdelivery.dto.CompleteOrderRequestDto;
import ru.delivery.fastdelivery.dto.CreateOrderRequest;
import ru.delivery.fastdelivery.dto.OrderDto;
import ru.delivery.fastdelivery.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public List<OrderDto> showOrders(@RequestParam(name = "limit", defaultValue = "10") Integer limit,
    								 @RequestParam(name = "offset", defaultValue = "0") Integer offset) {		
        return orderService.getAllOrders(limit, offset);
    }
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
    public List<OrderDto> createUser(@RequestBody CreateOrderRequest createOrderRequest){			
        return orderService.addListOrder(createOrderRequest);
    }
	
	
	@GetMapping("/{order_id}")
	public OrderDto findCourierById(@PathVariable("order_id") long order_id){		
		return orderService.getCourierById(order_id); 
	}
	
	
	@PostMapping("/complete")
	@ResponseStatus(HttpStatus.OK)
    public List<OrderDto> completeOrder(@RequestBody CompleteOrderRequestDto completeOrderRequestDto){
		List<OrderDto> orderDtoList = orderService.updateListOrder(completeOrderRequestDto);		
        return orderDtoList;
    }
	

	
}
