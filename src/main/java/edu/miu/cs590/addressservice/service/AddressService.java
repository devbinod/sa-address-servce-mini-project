package edu.miu.cs590.addressservice.service;

import edu.miu.cs590.addressservice.dto.AddressDto;
import edu.miu.cs590.addressservice.enitity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {


    AddressDto save(AddressDto addressDto);
    void deleteById (Long id);
    AddressDto update(AddressDto addressDto);
    List<AddressDto> findAllByUser(String username);
    AddressDto findById(Long id);
    AddressDto setDefaultBillingAddress(Long id);
    AddressDto setDefaultShippingAddress(Long id);


}
