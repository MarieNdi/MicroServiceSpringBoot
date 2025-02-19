package com.isi.school_management.user_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailPro;
    private String token;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private StudentEntity student;

    public String getEmailPro() {
        return emailPro;
    }

    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}