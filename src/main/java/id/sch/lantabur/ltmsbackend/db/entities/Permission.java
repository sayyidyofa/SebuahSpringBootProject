package id.sch.lantabur.ltmsbackend.db.entities;

import id.sch.lantabur.ltmsbackend.db.AuditableModel;
import id.sch.lantabur.ltmsbackend.rest.dto.permission.PlainPermission;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Setter
@Getter
public class Permission extends AuditableModel {

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "permissions")
    Set<Role> roles;

    public PlainPermission toPlain() {
        return new PlainPermission(name);
    }
}
