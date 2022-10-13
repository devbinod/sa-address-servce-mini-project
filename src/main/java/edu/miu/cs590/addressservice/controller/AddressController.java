package edu.miu.cs590.addressservice.controller;

import edu.miu.cs590.addressservice.dto.AddressDto;
import edu.miu.cs590.addressservice.service.AddressService;
import edu.miu.cs590.addressservice.util.AppUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {


    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressDto addressDto) {
        try {
            return ResponseEntity.ok(addressService.save(addressDto));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(addressService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAllUser() {
        try {
            return ResponseEntity.ok(addressService.findAllByUser(AppUtil.getCurrentUser()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            addressService.deleteById(id);
            return ResponseEntity.ok().body("Successfully Deleted");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        try {
            addressDto.setId(id);
            return ResponseEntity.ok().body(addressService.update(addressDto));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }


    @PutMapping("/set-default-billing-address/{id}")
    public ResponseEntity<?> setDefaultBillingAddress(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(addressService.setDefaultBillingAddress(id));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }


    @PutMapping("/set-default-shipping-address/{id}")
    public ResponseEntity<?> setDefaultShippingAddress(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(addressService.setDefaultShippingAddress(id));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

}
