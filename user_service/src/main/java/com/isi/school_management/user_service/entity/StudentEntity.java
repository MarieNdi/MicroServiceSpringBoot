package com.isi.school_management.user_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 255)
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 255)
    private String lastName;

    @Email(message = "Format d'email invalide")
    @Size(max = 255)
    private String emailPro;

    @Email(message = "Format d'email invalide")
    @Size(max = 255)
    private String emailPerso;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 255)
    private String address;

    @Builder.Default
    private boolean archive = false;

    @Size(max = 20)
    @Column(unique = true)
    private String registrationNu;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}