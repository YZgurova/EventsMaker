package com.EventsMaker.v1.payments;

import com.EventsMaker.v1.auth.MyUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/charge")
@SecurityRequirement(name = "bearerAuth")
public class StripeChargeController {

    @Resource
    private StripeChargeService service;

    @PostMapping
    public ResponseEntity<String> createCharge(@AuthenticationPrincipal MyUser user, @RequestBody StripeCharge stripeCharge) {
       return service.createCharge(user, stripeCharge);
    }
}
