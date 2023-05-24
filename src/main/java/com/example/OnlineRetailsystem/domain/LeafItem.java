package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "LeafItems")
@Data
public class LeafItem extends Item {
}
