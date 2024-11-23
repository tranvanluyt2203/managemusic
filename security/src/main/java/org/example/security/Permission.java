package org.example.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String permistion;

    public Long getId() {
        return id;
    }

    public String getPermistion() {
        return permistion;
    }

    public void setPermistion(String permistion) {
        this.permistion = permistion;
    }

}

