package id.sch.lantabur.ltmsbackend.aspects;

import id.sch.lantabur.ltmsbackend.db.BaseEntity;
import id.sch.lantabur.ltmsbackend.rest.services.Roti;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BreadAspect {

    @Around("@within(id.sch.lantabur.ltmsbackend.aspects.Kneadable)")
    public <T extends BaseEntity, R extends JpaRepository<T, Long>> Object checkAuthorities(ProceedingJoinPoint jp) throws Throwable {

        var target = jp.getTarget();

        if (target instanceof Roti) {
            @SuppressWarnings("unchecked") Roti<T, R> roti = (Roti<T, R>) target;
            roti.getProfileManager().getProfiles().forEach(p -> p.getRoles().forEach(System.out::println));

            if (roti.getProfileManager().getProfiles().stream().anyMatch(p -> p.getPermissions().contains(jp.getSignature().getName()))) {
                return jp.proceed();
            } else {
                throw new AccessDeniedException("No suitable permissions found");
            }
        } else throw new Exception(target + " is not instanceof Roti");
    }
}
