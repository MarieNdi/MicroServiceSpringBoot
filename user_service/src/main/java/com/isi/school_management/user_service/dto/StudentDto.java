package com.isi.school_management.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record StudentDto(
    Long id,
    
    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 255)
    String firstName,
    
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 255)
    String lastName,
    
    @Email(message = "Format d'email invalide")
    String emailPro,
    
    @Email(message = "Format d'email invalide")
    String emailPerso,
    
    String phoneNumber,
    String address,
    boolean archive,
    String registrationNu,
    UserDto user
) {}