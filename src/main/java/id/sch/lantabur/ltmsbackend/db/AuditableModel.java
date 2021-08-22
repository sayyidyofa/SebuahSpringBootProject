package id.sch.lantabur.ltmsbackend.db;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@EntityListeners(JpaEventListener.class)
public abstract class AuditableModel extends BaseEntity {
}
