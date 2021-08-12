package id.sch.lantabur.ltmsbackend.config;

import id.sch.lantabur.ltmsbackend.db.entities.Pengguna;
import id.sch.lantabur.ltmsbackend.db.repositories.PenggunaRepository;
import id.sch.lantabur.ltmsbackend.util.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// this approach is also possible https://stackoverflow.com/a/44923402/8885105
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AfterStartup implements ApplicationListener<ContextRefreshedEvent> {

    private final PenggunaRepository penggunaRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!event.getApplicationContext().getBean("isProd", boolean.class)) {

            penggunaRepository.save(new Pengguna("sayyidyofa", "password", Role.ADMIN));

        }
    }
}
