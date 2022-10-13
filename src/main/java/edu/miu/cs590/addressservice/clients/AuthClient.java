package edu.miu.cs590.addressservice.clients;

import edu.miu.cs590.addressservice.dto.VerifyUserTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name = "${feign.auth.service.name}", url = "${feign.auth.service.url}")

@Component
public interface AuthClient {

    @PostMapping("/verity-token")
    boolean verifyUser(VerifyUserTokenDto verifyUserTokenDto);
}
