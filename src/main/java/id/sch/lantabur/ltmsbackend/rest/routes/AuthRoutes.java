package id.sch.lantabur.ltmsbackend.rest.routes;

import id.sch.lantabur.ltmsbackend.rest.exceptions.ResourceNotFoundException;
import id.sch.lantabur.ltmsbackend.rest.validation.security.Loginable;
import org.mindrot.jbcrypt.BCrypt;
import id.sch.lantabur.ltmsbackend.db.repositories.PenggunaRepository;
import org.pac4j.core.profile.UserProfile;
import org.pac4j.jwt.profile.JwtGenerator;
import org.pac4j.jwt.profile.JwtProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class AuthRoutes {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private PenggunaRepository penggunaRepository;

    @PostMapping(path = "/login", consumes = "application/json")
    public String authenticateAndGenerateToken(@RequestBody @Valid Loginable loginable) {
        var user = penggunaRepository.findByUsername(loginable.username);

        if (user == null) {
            throw new ResourceNotFoundException(String.format("No user found for username (%s)", loginable.username));
        } else {
            UserProfile profile = new JwtProfile();
            profile.setId(user.getId().toString());
            profile.addRole(user.getRole().name().toUpperCase());
            return BCrypt.checkpw(loginable.password, user.getPassword())
                    ? jwtGenerator.generate(profile)
                    : "failed";
        }
    }
}
