package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService_serv {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleService_serv(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> all() {
        return this.roleRepository.findAll();
    }

    public void add(Role e) {

        this.roleRepository.save(e);
    }

    public Role update(Long id, Role e) {
        return this.roleRepository.findById(id)
                .map(p -> {
                    if (p.getName() != null)
                        p.setName(e.getName());
                    /*if (p.getAccounts() != null)
                        p.setAccounts(e.getAccounts());*/
                    return this.roleRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Role id not found"));
    }

    public void remove(Long id) {

        Role role = this.roleRepository.findById(id).get();

        if (role != null)
            this.roleRepository.delete(role);
    }

    public Role getById(Long id) {
        return this.roleRepository.findById(id).orElseThrow(()-> new RuntimeException("Attention role not found"));
    }
}
