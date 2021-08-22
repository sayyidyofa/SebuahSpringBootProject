package id.sch.lantabur.ltmsbackend.rest.services;

import id.sch.lantabur.ltmsbackend.db.entities.Pengguna;
import id.sch.lantabur.ltmsbackend.db.repositories.PenggunaRepository;
import id.sch.lantabur.ltmsbackend.rest.dto.kejadian.PlainEvent;
import id.sch.lantabur.ltmsbackend.rest.dto.pengguna.PenggunaDto;
import id.sch.lantabur.ltmsbackend.rest.validation.pengguna.PenggunaCreate;
import id.sch.lantabur.ltmsbackend.rest.validation.pengguna.PenggunaUpdate;
import id.sch.lantabur.ltmsbackend.util.enums.EventModelType;
import id.sch.lantabur.ltmsbackend.util.enums.EventType;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    @NonNull
    private AuditService auditService;

    @NonNull
    private PenggunaRepository penggunaRepository;

    public PenggunaDto getPenggunaById(Long id) {
        var pengguna = penggunaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new PenggunaDto(pengguna.getUsername(), pengguna.getRole());
    }

    public List<PenggunaDto> getAllPengguna() {
        return penggunaRepository
                .findAll()
                .stream()
                .map(Pengguna::toPenggunaDto)
                .toList();
    }

    @Transactional
    public PlainEvent saveNewPengguna(PenggunaCreate calonPengguna, ProfileManager profileManager) {
        var garam = BCrypt.gensalt();
        calonPengguna.password = BCrypt.hashpw(calonPengguna.password, garam);

        return auditService.saveAudit(
                profileManager,
                EventType.CREATE,
                penggunaRepository.save(calonPengguna.build())
                ).toPlainEvent();
    }

    @Transactional
    public PlainEvent updateExistingPengguna(PenggunaUpdate lePengguna, ProfileManager profileManager) {
        var pengguna = penggunaRepository.findById(lePengguna.id).orElseThrow(EntityNotFoundException::new);
        var garam = BCrypt.gensalt();

        pengguna.setUsername(lePengguna.username);
        if (lePengguna.password != null) pengguna.setPassword(BCrypt.hashpw(lePengguna.password, garam));
        if (lePengguna.role != null) pengguna.setRole(lePengguna.role);

        return auditService.saveAudit(profileManager, EventType.UPDATE, penggunaRepository.save(pengguna))
                .toPlainEvent();
    }

    @Transactional
    public PlainEvent removePengguna(Long id, ProfileManager profileManager) {
        return auditService.saveAudit(profileManager, EventType.DELETE, EventModelType.PENGGUNA, id)
                .toPlainEvent();
    }
}
