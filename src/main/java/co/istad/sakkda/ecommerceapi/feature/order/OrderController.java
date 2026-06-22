package co.istad.sakkda.ecommerceapi.feature.order;

import co.istad.sakkda.ecommerceapi.feature.order.dto.CreateOrderRequest;
import co.istad.sakkda.ecommerceapi.feature.order.dto.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse createNew(
            @Valid @RequestBody CreateOrderRequest createOrderRequest,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return orderService.createNew(createOrderRequest, jwt);
    }
}
