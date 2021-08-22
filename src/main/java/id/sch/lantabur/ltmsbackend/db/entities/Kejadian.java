package id.sch.lantabur.ltmsbackend.db.entities;

import id.sch.lantabur.ltmsbackend.db.BaseEntity;
import id.sch.lantabur.ltmsbackend.rest.dto.kejadian.PlainEvent;
import id.sch.lantabur.ltmsbackend.util.enums.EventModelType;
import id.sch.lantabur.ltmsbackend.util.enums.EventType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Kejadian extends BaseEntity {

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
    @JoinColumn(nullable = false)
    @NonNull
    private Pengguna actor;

    @Column(nullable = false)
    private LocalDateTime time = LocalDateTime.now();

    public PlainEvent toPlainEvent() {
        return new PlainEvent(type.name(), target.name());
    }
}
