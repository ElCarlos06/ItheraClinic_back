package com.example.demo.modules.user.roles;

import com.example.demo.kernel.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "roles")
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(name = "description")
    private String description;
}
