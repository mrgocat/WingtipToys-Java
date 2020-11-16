package com.wingtip.webapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wingtip.webapi.repository.entities.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>{
	List<OrderEntity> findByUserId(String userId, Sort sort);
	@Query(value="select o from orders o where o.userId = ?1 and o.status <> 'CANCELED' order by o.orderDate desc")
	List<OrderEntity> findByUserIdNotCancelled(String userId);
}
