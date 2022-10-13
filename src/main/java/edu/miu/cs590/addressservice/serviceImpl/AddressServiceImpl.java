package edu.miu.cs590.addressservice.serviceImpl;

import edu.miu.cs590.addressservice.dto.AddressDto;
import edu.miu.cs590.addressservice.enitity.Address;
import edu.miu.cs590.addressservice.exception.AddressNotFound;
import edu.miu.cs590.addressservice.mapper.AddressMapper;
import edu.miu.cs590.addressservice.repository.AddressRepository;
import edu.miu.cs590.addressservice.service.AddressService;
import edu.miu.cs590.addressservice.util.AppUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDto save(AddressDto addressDto) {

        List<Address> addresses = addressRepository.findAllByUsername(AppUtil.getCurrentUser());

        if (addresses.size() == 0) {
            addressDto.setDefaultAddress(true);
            addressDto.setShippingAddress(true);
            addressDto.setBillingAddress(true);
        }

        return addressMapper.toDto(addressRepository.save(
                getCurrentAddress(addressDto)
        ));


    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(findByUsernameAndId(id).getId());

    }

    @Override
    public AddressDto update(AddressDto addressDto) {

        findByUsernameAndId(addressDto.getId());
        return addressMapper.toDto(addressRepository.save(
                getCurrentAddress(addressDto)
        ));

    }

    @Override
    public List<AddressDto> findAllByUser(String username) {

        return addressMapper.toDtoList(addressRepository.findAllByUsername(username));
    }

    @Override
    public AddressDto findById(Long id) {
        return addressMapper.toDto(findByUsernameAndId(id));
    }


    @Override
    public AddressDto setDefaultBillingAddress(Long id) {
        Address address = findByUsernameAndId(id);
        address.setBillingAddress(true);
        List<Address> addresses = addressRepository.findAllByUsername(AppUtil.getCurrentUser()).stream().map(it -> {
            if (it != address) {
                it.setBillingAddress(false);
            }
            return it;
        }).toList();
        addressRepository.saveAll(addresses);
        return addressMapper.toDto(addressRepository.save(address));
    }

    @Override
    public AddressDto setDefaultShippingAddress(Long id) {

        Address address = findByUsernameAndId(id);
        address.setShippingAddress(true);
        List<Address> addresses = addressRepository.findAllByUsername(AppUtil.getCurrentUser()).stream().map(it -> {
            if (it != address) {
                it.setShippingAddress(false);
            }
            return it;
        }).toList();
        addressRepository.saveAll(addresses);

        return addressMapper.toDto(addressRepository.save(address));
    }

    private Address getCurrentAddress(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        address.setUsername(AppUtil.getCurrentUser());
        return address;
    }

    private Address findByUsernameAndId(Long id) {
        Optional<Address> address = addressRepository.findByIdAndUsername(id, AppUtil.getCurrentUser());
        if (address.isEmpty()) throw new AddressNotFound("Address not found");
        return address.get();
    }
}
