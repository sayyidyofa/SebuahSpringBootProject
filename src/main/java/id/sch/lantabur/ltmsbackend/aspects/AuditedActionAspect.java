package id.sch.lantabur.ltmsbackend.aspects;

import id.sch.lantabur.ltmsbackend.db.AuditableModel;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditedActionAspect {
    @AfterReturning(value = "@annotation(AuditedAction)", returning = "savedModel")
    public <M extends AuditableModel> void auditModel(M savedModel) {

    }
}
