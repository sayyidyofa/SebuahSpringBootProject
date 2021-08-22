package id.sch.lantabur.ltmsbackend.rest.validation.pengguna;

import id.sch.lantabur.ltmsbackend.util.enums.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PenggunaUpdate {
    @NotNull
    @Positive
    public Long id;

    @NotBlank
    public String username;

    public String password;

    public Role role;
}
