package id.sch.lantabur.ltmsbackend.db.entities;

import id.sch.lantabur.ltmsbackend.db.AuditableModel;
import id.sch.lantabur.ltmsbackend.rest.dto.role.RoleDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Role extends AuditableModel {

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ROLE_PERMISSION",
            joinColumns = @JoinColumn(name = "ROLE_id"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_id"))
    private List<Permission> permissions;

    public RoleDto toRoleDto() {
        return new RoleDto(name, description);
    }
}