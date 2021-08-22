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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@ComponentScan(basePackages = { "org.pac4j.springframework.annotation", "org.pac4j.springframework.component" })
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoutes {

    @NonNull
    private ProfileManager profileManager;

    @NonNull
    private UserService userService;

    @GetMapping("/{id}")
    public PenggunaDto getById (
            @PathVariable Long id) {
        return userService.getPenggunaById(id);
    }

    @GetMapping
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
