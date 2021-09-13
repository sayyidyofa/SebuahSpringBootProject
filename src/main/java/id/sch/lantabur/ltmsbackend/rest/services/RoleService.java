package id.sch.lantabur.ltmsbackend.rest.services;

import id.sch.lantabur.ltmsbackend.db.entities.Role;
import id.sch.lantabur.ltmsbackend.db.repositories.RoleRepository;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends Roti<Role, RoleRepository>{

    @Autowired
    public RoleService(RoleRepository roleRepository, ProfileManager profileManager) {
        super(roleRepository, profileManager, "role");
    }


    // nyoba autowired generic di final field dan dynamic rbac
}
