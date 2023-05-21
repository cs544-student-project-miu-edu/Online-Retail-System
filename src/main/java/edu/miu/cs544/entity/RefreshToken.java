package edu.miu.cs544.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class RefreshToken {
    @Id
    String token;

    @OneToMany(mappedBy = "refreshToken", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<AccessToken> accessTokens;
}