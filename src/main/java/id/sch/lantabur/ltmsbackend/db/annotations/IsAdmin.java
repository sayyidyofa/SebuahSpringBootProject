package id.sch.lantabur.ltmsbackend.db.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('T(id.sch.lantabur.ltmsbackend.util.enums.Role).ADMIN.name()')")
public @interface IsAdmin {
}
