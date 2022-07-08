package com.engidash.auth.controllers;

import com.engidash.auth.dto.RegistrationRequestDTO;
import com.engidash.auth.services.registarion.RegistrationService;
import com.engidash.auth.services.response.ResponseBuilderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ResponseBuilderService responseBuilderService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequestDTO request) {
        try {
            Optional<String> token = Optional.ofNullable(registrationService.register(request));
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("token", token);
            responseObject.put("user", request);
            return responseBuilderService.generateResponse(
                    "You were registered successfully!",
                    HttpStatus.OK,
                    responseObject);
        } catch (Exception e) {
            return responseBuilderService.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        try {
            boolean isConfirmed = registrationService.confirmToken(token);
            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("isUserEnabled", isConfirmed);
            return responseBuilderService.generateResponse(
                    "You have successfully confirmed your email!",
                    HttpStatus.OK,
                    responseObject);
        } catch (Exception e) {
            return responseBuilderService.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }
}
