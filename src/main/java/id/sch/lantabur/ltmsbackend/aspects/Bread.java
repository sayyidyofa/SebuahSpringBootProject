package id.sch.lantabur.ltmsbackend.aspects;


import org.pac4j.core.profile.ProfileManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Kneadable
public interface Bread<T, N, R> {

    Iterable<T> browse();

    Iterable<T> browse(Sort sort);

    Page<T> browse(Pageable pageable);

    Optional<T> read(N identifier);

    N edit(T editable);

    N add(T addable);

    N delete(T deletable);

}
