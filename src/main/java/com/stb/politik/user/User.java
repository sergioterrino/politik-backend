package com.stb.politik.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 25)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "lastname", nullable = false, length = 30)
    private String lastname;

    @Column(name = "phone", unique = true, nullable = true, length = 9)
    private String phone;

    @Column(name = "email", unique = true, nullable = true, length = 50)
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "birthday", nullable = true)
    private LocalDate birthday;

    @Column(name = "dni", length = 9)
    private String dni;

    private String rol;

    @Override
    public String toString() {
        return "User [Id=" + id + ", username=" + username + ", rol=" + rol + ", name=" + name + ", lastname="
                + lastname + ", phone=" + phone + ", email=" + email + ", createdAt=" + createdAt + ", birthday="
                + birthday + ", dni=" + dni + "]";
    }
}