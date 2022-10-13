package edu.miu.cs590.addressservice.mapper;

import edu.miu.cs590.addressservice.dto.AddressDto;
import edu.miu.cs590.addressservice.enitity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {


    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);

    List<Address> toListEntity(List<AddressDto> addressDto);

    List<AddressDto> toDtoList(List<Address> address);
}
