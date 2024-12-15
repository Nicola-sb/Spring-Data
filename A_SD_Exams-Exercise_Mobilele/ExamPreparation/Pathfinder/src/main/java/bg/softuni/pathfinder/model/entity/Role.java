package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.model.entity.enums.RoleNameEnums;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{


    @Enumerated(EnumType.STRING)
    private RoleNameEnums role;

    public RoleNameEnums getRole() {
        return role;
    }

    public void setRole(RoleNameEnums role) {
        this.role = role;
    }
}
