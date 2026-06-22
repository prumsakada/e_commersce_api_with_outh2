package co.istad.sakkda.ecommerceapi.feature.order;

import co.istad.sakkda.ecommerceapi.feature.order.dto.CreateOrderRequest;
import co.istad.sakkda.ecommerceapi.feature.order.dto.OrderResponse;
import co.istad.sakkda.ecommerceapi.feature.product.Product;
import co.istad.sakkda.ecommerceapi.feature.product.ProductRepository;
import co.istad.sakkda.ecommerceapi.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {

        List<OrderLine> validOrderLine =  new ArrayList<>();
        final Order order = orderMapper.mapCreateOrderRequestToOrder(createOrderRequest);

       boolean isValid = createOrderRequest.orderLines().stream()
                .allMatch( orderLineDto -> {
                    boolean isExisting = productRepository.existsById(orderLineDto.productCode());
                    if(isExisting){
                        Product validProduct = productRepository.findById(orderLineDto.productCode())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "product code has been found"));
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(validProduct);
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setDiscount(orderLineDto.discount());
                        orderLine.setOrder(order);
                        validOrderLine.add(orderLine);
                    }
                    return isExisting;
                });

       if(!isValid)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND
           ,"product code is invalid");
       order.setOrderedAt(Instant.now());
       order.setIsDeleted(false);
//       order.setOrderedBy(jwt.getSubject());
//       order.setOrderedBy(jwt.getClaimAsString("preferred_username");
       order.setOrderedBy(AuthUtil.extractUserId());
       order.setOrderLines(validOrderLine);

       Order saveOrder = orderRepository.save(order);

        return orderMapper.mapOrderToResponse(saveOrder);
    }
}
