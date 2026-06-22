package co.istad.sakkda.ecommerceapi.feature.order;

import co.istad.sakkda.ecommerceapi.feature.order.dto.CreateOrderRequest;
import co.istad.sakkda.ecommerceapi.feature.order.dto.OrderLineDto;
import co.istad.sakkda.ecommerceapi.feature.order.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order mapCreateOrderRequestToOrder(CreateOrderRequest createOrderRequest);

    OrderResponse mapOrderToResponse(Order order);


    default OrderLineDto mapOrderLineToOrderLineDto(OrderLine orderLine){
        return OrderLineDto.builder()
                .productCode(orderLine.getProduct().getCode())
                .qty(orderLine.getQty())
                .discount(orderLine.getDiscount())
                .build();
    }

//    default List<OrderLineDto> mapOrderLineToOrderLineDto(List<OrderLine> orderLine){
//        return orderLine.stream()
//                .map(orderLine -> OrderLineDto.builder()
//                        .productCode(orderLine.getProduct().getCode())
//                        .qty(orderLine.getQty())
//                        .discount(orderLine.getDiscount())
//                        .build())
//                .collect(Collectors.toList());
//    }
}
