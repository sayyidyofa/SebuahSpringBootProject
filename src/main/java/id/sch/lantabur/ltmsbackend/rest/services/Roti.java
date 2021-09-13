package id.sch.lantabur.ltmsbackend.rest.services;

import id.sch.lantabur.ltmsbackend.aspects.Bread;
import id.sch.lantabur.ltmsbackend.db.BaseEntity;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class Roti<T extends BaseEntity, R extends JpaRepository<T, Long>> implements Bread<T, Long, R> {

    protected final R repository;

    protected final ProfileManager profileManager;

    protected final String entityClassName;

    protected Roti(R repo, ProfileManager profileManager, String entityClassName) {
        repository = repo;
        this.profileManager = profileManager;
        this.entityClassName = entityClassName;
    }

    @Override
    public List<T> browse() {
        return repository.findAll();
    }

    @Override
    public List<T> browse(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<T> browse(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<T> read(Long identifier) {
        return repository.findById(identifier);
    }

    @Override @Transactional
    public Long edit(T editable) {
        return repository.save(editable).getId();
    }

    @Override @Transactional
    public Long add(T addable) {
        return repository.save(addable).getId();
    }

    @Override @Transactional
    public Long delete(T deletable) {
        Long id = deletable.getId();
        repository.delete(deletable);
        return id;
    }

    public R getRepo() {
        return repository;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public String getEntityClassName() {
        return entityClassName;
    }
}
