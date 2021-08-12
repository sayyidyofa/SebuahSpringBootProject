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
    //private final ServiceRegistry serviceRegistry;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!event.getApplicationContext().getBean("isProd", boolean.class)) {
            //ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

            /*MetadataSources metadata = new MetadataSources(serviceRegistry);
            metadata.addAnnotatedClass(Alquran.class);
            metadata.addAnnotatedClass(Kejadian.class);
            metadata.addAnnotatedClass(Pengguna.class);

            EnumSet<TargetType> enumSet = EnumSet.of(TargetType.DATABASE);
            SchemaExport schemaExport = new SchemaExport();
            schemaExport.execute(enumSet, SchemaExport.Action.BOTH, metadata.buildMetadata());*/

            penggunaRepository.save(new Pengguna("sayyidyofa", "password", Role.ADMIN));

        }
    }
}
