package ru.delivery.fastdelivery.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.delivery.fastdelivery.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
