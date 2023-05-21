package edu.miu.cs544.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AccessToken {
    @Id
    String token;
     @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value="accessToken-refreshToken")
    RefreshToken refreshToken;
}
