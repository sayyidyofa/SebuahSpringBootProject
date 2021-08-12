package id.sch.lantabur.ltmsbackend.db.entities;

import id.sch.lantabur.ltmsbackend.db.ListenableEntity;
import id.sch.lantabur.ltmsbackend.util.enums.EventModelType;
import id.sch.lantabur.ltmsbackend.util.enums.EventType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
public class Kejadian extends ListenableEntity {

    @Enumerated
    @Column(nullable = false)
    @NonNull
    private EventType type;

    @Enumerated
    @Column(nullable = false)
    @NonNull
    private EventModelType target;

    @Column(nullable = false)
    @NonNull
    private Long targetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Pengguna actor;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime time = LocalDateTime.now();
}
