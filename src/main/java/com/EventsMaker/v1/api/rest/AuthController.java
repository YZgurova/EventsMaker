package com.EventsMaker.v1.api.rest;

import com.EventsMaker.v1.models.Authentication;
import com.EventsMaker.v1.models.*;
import com.EventsMaker.v1.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
//@SecurityRequirement(name = "bearerAuth")
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping(value = "/login", consumes = {"application/json"})
	public Authentication login(@RequestBody LoginInput input) {
		return authService.login(input.username, input.password);
	}

	@PostMapping(value = "/register", consumes = {"application/json"})
	public Authentication register(@RequestBody RegisterInput input) {
		authService.register(input.username, input.password, input.email);
		return authService.login(input.username, input.password);
	}
}
