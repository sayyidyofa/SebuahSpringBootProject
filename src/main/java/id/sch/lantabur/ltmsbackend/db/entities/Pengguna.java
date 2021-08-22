package id.sch.lantabur.ltmsbackend.db.entities;

import id.sch.lantabur.ltmsbackend.db.AuditableModel;
import id.sch.lantabur.ltmsbackend.util.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Pengguna extends AuditableModel {

    @Column(nullable = false)
    @NonNull
    private String username;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Enumerated
    @Column(nullable = false)
    @NonNull
    private Role role;

    @OneToMany(mappedBy = "actor")
    private Set<Kejadian> auditLogs;
}
