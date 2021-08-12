package id.sch.lantabur.ltmsbackend.db;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(EntityCUDListener.class)
public abstract class ListenableEntity extends BaseEntity {

}
