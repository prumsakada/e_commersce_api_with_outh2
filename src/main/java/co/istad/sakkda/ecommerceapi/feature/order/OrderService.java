package co.istad.sakkda.ecommerceapi.feature.order;

import co.istad.sakkda.ecommerceapi.feature.order.dto.CreateOrderRequest;
import co.istad.sakkda.ecommerceapi.feature.order.dto.OrderResponse;
import org.springframework.security.oauth2.jwt.Jwt;

public interface OrderService {

    OrderResponse createNew(CreateOrderRequest createOrderRequest);
}
