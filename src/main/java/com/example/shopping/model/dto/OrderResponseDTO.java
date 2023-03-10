package com.example.shopping.model.dto;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponseDTO {

    private int orderId;

    private String orderDate;

    private BigDecimal totalPrice;

    private String address;

    private String phoneNumber;

    private String name;

    private Set<ProductResponseDTO> products;

    private Set<OrderDetailsResponseDTO> orderDetails;

}
