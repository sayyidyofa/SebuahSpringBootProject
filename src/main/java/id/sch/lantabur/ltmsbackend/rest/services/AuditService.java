package id.sch.lantabur.ltmsbackend.rest.services;


import id.sch.lantabur.ltmsbackend.db.AuditableModel;
import id.sch.lantabur.ltmsbackend.db.entities.Kejadian;
import id.sch.lantabur.ltmsbackend.db.entities.Pengguna;
import id.sch.lantabur.ltmsbackend.db.repositories.KejadianRepository;
import id.sch.lantabur.ltmsbackend.db.repositories.PenggunaRepository;
import id.sch.lantabur.ltmsbackend.util.enums.EventModelType;
import id.sch.lantabur.ltmsbackend.util.enums.EventType;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuditService {
    @NonNull
    private PenggunaRepository penggunaRepository;

    @NonNull
    private KejadianRepository kejadianRepository;

    @Transactional
    public <E extends AuditableModel> Kejadian saveAudit(ProfileManager profileManager, EventType eventType, E entity) {
        return kejadianRepository.save(new Kejadian(
                eventType,
                EventModelType.valueOf(entity.getClass().getSimpleName().toUpperCase()),
                entity.getId(),
                getPrincipal(profileManager)
        ));
    }

    @Transactional
    public Kejadian saveAudit(ProfileManager profileManager, EventType eventType, EventModelType eventModelType, Long entityId) {
        return kejadianRepository.save(new Kejadian(
                eventType,
                eventModelType,
                entityId,
                getPrincipal(profileManager)
        ));
    }

    private Pengguna getPrincipal(ProfileManager profileManager) {
        return penggunaRepository
                .findById(
                        Long.parseLong(
                                profileManager
                                        .getProfile()
                                        .orElseThrow(() -> new EntityNotFoundException("User profile not found"))
                                        .getId()
                        )
                ).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
