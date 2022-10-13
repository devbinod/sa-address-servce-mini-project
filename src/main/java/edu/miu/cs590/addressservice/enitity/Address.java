package edu.miu.cs590.addressservice.enitity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
