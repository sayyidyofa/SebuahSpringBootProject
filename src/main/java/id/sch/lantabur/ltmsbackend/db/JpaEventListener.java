package id.sch.lantabur.ltmsbackend.db;

import id.sch.lantabur.ltmsbackend.db.entities.Kejadian;
import id.sch.lantabur.ltmsbackend.db.repositories.KejadianRepository;
import id.sch.lantabur.ltmsbackend.db.repositories.PenggunaRepository;
import id.sch.lantabur.ltmsbackend.util.enums.EventModelType;
import id.sch.lantabur.ltmsbackend.util.enums.EventType;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@ComponentScan("org.pac4j")
public class JpaEventListener {

    @Autowired
    private ProfileManager profileManager;

    @Autowired
    private KejadianRepository kejadianRepository;

    @Autowired
    private PenggunaRepository penggunaRepository;

    @PostLoad
    public void postLoad(Object o) {

    }

    @PostRemove
    public <E extends AuditableModel> void postRemove(E entity) {
        var profile = profileManager.getProfile();
        var actorId = Long.parseLong(profile.isPresent() ? profile.get().getId() : "1");
        var actor = penggunaRepository.getById(actorId);

        kejadianRepository.save(new Kejadian(
                EventType.DELETE,
                EventModelType.valueOf(entity.getClass().getSimpleName().toUpperCase()),
                entity.getId(),
                actor
        ));
    }

    @PostUpdate
    public <E extends AuditableModel> void postUpdate(E entity) {
        var profile = profileManager.getProfile();
        var actorId = Long.parseLong(profile.isPresent() ? profile.get().getId() : "1");
        var actor = penggunaRepository.getById(actorId);

        kejadianRepository.save(new Kejadian(
                EventType.UPDATE,
                EventModelType.valueOf(entity.getClass().getSimpleName().toUpperCase()),
                entity.getId(),
                actor
        ));
    }

    @PostPersist
    public <E extends AuditableModel> void postPersist(E entity) {
        var profile = profileManager.getProfile();
        var actorId = Long.parseLong(profile.isPresent() ? profile.get().getId() : "1");
        var actor = penggunaRepository.getById(actorId);

        kejadianRepository.save(new Kejadian(
                EventType.CREATE,
                EventModelType.valueOf(entity.getClass().getSimpleName().toUpperCase()),
                entity.getId(),
                actor
        ));
    }
}
