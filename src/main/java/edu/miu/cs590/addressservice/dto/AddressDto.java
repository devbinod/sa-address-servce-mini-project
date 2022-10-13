package edu.miu.cs590.addressservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String city;
    private int zipCode;
    private String state;
    private String street;
    private String username;
    private boolean isDefaultAddress;
    private boolean isShippingAddress;
    private boolean isBillingAddress;
}
