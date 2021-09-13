package id.sch.lantabur.ltmsbackend.rest.routes;

import id.sch.lantabur.ltmsbackend.rest.dto.role.RoleDto;
import id.sch.lantabur.ltmsbackend.rest.services.RoleService;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleRoutes {
    @NonNull
    private ProfileManager profileManager;

    @NonNull
    private RoleService roleService;

    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable Long id) {
        return roleService.read(id).orElseThrow().toRoleDto();
    }
}
