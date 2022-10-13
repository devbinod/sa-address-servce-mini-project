package edu.miu.cs590.addressservice.repository;

import edu.miu.cs590.addressservice.enitity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findAllByUsername(String username);
    Optional<Address> findByIdAndUsername(Long id,String username);
}
