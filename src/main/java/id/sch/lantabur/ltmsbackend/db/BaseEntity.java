package id.sch.lantabur.ltmsbackend.db;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter
    private Long id;

}
