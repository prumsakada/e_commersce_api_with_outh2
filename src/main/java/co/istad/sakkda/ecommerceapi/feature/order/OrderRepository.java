package co.istad.sakkda.ecommerceapi.feature.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository
extends JpaRepository<Order, UUID> {
}
