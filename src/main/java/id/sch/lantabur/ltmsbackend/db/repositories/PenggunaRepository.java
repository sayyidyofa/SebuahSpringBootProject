package id.sch.lantabur.ltmsbackend.db.repositories;

import id.sch.lantabur.ltmsbackend.db.entities.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
}
