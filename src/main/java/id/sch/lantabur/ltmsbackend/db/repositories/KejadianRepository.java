package id.sch.lantabur.ltmsbackend.db.repositories;

import id.sch.lantabur.ltmsbackend.db.entities.Kejadian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KejadianRepository extends JpaRepository<Kejadian, Long> {
}
