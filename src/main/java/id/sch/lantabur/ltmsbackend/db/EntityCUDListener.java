package id.sch.lantabur.ltmsbackend.db;

import id.sch.lantabur.ltmsbackend.db.entities.Pengguna;

import javax.persistence.*;

public class EntityCUDListener {
    @PostRemove
    public void postRemove(Object o) {

    }

    @PostUpdate
    public void postUpdate(Object o) {

    }

    @PostPersist
    public void postPersist(ListenableEntity entity) {
        System.out.println(entity.getClass().getSimpleName());
        System.out.println(entity);
    }

}
