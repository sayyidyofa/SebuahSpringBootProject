package id.sch.lantabur.ltmsbackend.rest.routes;

import id.sch.lantabur.ltmsbackend.rest.dto.kejadian.PlainEvent;
import id.sch.lantabur.ltmsbackend.rest.dto.pengguna.PenggunaDto;
import id.sch.lantabur.ltmsbackend.rest.services.UserService;
import id.sch.lantabur.ltmsbackend.rest.validation.pengguna.PenggunaCreate;
import id.sch.lantabur.ltmsbackend.rest.validation.pengguna.PenggunaUpdate;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoutes {

    public boolean userHasAuthority(Authentication authentication, String authority)
    {
        var authorities = authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

    @NonNull
    private ProfileManager profileManager;

    @NonNull
    private UserService userService;

    @GetMapping("/{id}")
    public PenggunaDto getById (
            @PathVariable Long id) {
        System.out.println(profileManager.getProfile().orElseThrow().getRoles());
        return userService.getPenggunaById(id);
    }

    @GetMapping
    @PreAuthorize("userRoutes.userHasAuthority(authentication, 'ADMIN')")
    public List<PenggunaDto> all () {
        return userService.getAllPengguna();
    }

    @PostMapping(consumes = "application/json")
    public PlainEvent save(
            @RequestBody
            @Valid PenggunaCreate calonPengguna) {
        return userService.saveNewPengguna(calonPengguna, profileManager);
    }

    @PutMapping(consumes = "application/json")
    public PlainEvent update(
            @RequestBody
            @Valid PenggunaUpdate lePengguna) {
        return userService.updateExistingPengguna(lePengguna, profileManager);
    }

    @DeleteMapping("/{id}")
    public PlainEvent delete(
            @PathVariable Long id) {
        return userService.removePengguna(id, profileManager);
    }
}
