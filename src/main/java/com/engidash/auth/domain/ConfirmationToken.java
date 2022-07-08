package com.engidash.auth.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table(name = "\"CONFIRMATION_TOKEN\"")
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "\"ID\"", nullable = false)
    private UUID id;
    @Column(name = "\"TOKEN\"", nullable = false)
    @OrderColumn
    private String token;
    @Column(name = "\"CREATED_AT\"", nullable = false)
    @OrderColumn
    private LocalDateTime createdAt;
    @Column(name = "\"EXPIRES_AT\"", nullable = false)
    @OrderColumn
    private LocalDateTime expiresAt;
    @Column(name = "\"CONFIRMED_AT\"")
    @OrderColumn
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "\"APP_USER_ID\"",
            foreignKey = @ForeignKey(name = "\"FK_APP_USER_ID\"")
    )
    private AppUser appUser;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
