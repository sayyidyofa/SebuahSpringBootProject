package id.sch.lantabur.ltmsbackend.rest.validation.pengguna;

import id.sch.lantabur.ltmsbackend.db.entities.Pengguna;
import id.sch.lantabur.ltmsbackend.util.enums.Role;
import id.sch.lantabur.ltmsbackend.util.enums.ValueOfEnum;

import javax.validation.constraints.NotBlank;

public class PenggunaCreate {
    @NotBlank
    public String username;

    @NotBlank
    public String password;

    @ValueOfEnum(Role.class)
    public String role;

    public Pengguna build() {
        return new Pengguna(username, password, Role.valueOf(role));
    }
}
