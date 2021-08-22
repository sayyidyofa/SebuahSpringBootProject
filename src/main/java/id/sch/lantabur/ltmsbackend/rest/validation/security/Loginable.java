package id.sch.lantabur.ltmsbackend.rest.validation.security;

import javax.validation.constraints.NotBlank;

public class Loginable {

    @NotBlank
    public String username;

    @NotBlank
    public String password;

    public Loginable(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
