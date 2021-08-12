package id.sch.lantabur.ltmsbackend.db.entities;

import id.sch.lantabur.ltmsbackend.db.ListenableEntity;
import id.sch.lantabur.ltmsbackend.util.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
public class Pengguna extends ListenableEntity {

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

    @OneToMany(targetEntity = Kejadian.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Kejadian> auditLogs;
}
