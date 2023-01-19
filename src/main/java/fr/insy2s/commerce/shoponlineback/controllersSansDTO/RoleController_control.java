package fr.insy2s.commerce.shoponlineback.controllersSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.RoleService_serv;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController_control {

    private final RoleService_serv roleServiceServ;

    @GetMapping("/all-role")
    public List<Role> AllRole()
    {
        return this.roleServiceServ.all();
    }

    @PostMapping("/add-role")
    public String addRole(@Validated @RequestBody Role role)
    {
        this.roleServiceServ.add(role);

        return "rol successfully add";
    }

    @PutMapping("/update-role/{idRole}")
    public String updateRole(@Validated @PathVariable Long idRole, @RequestBody Role role)
    {
        this.roleServiceServ.update(idRole, role);

        return "role update complete successfully";
    }

    @DeleteMapping("/remove-role/{idRole}")
    public String removeRole(@Validated @PathVariable Long idRole)
    {
        this.roleServiceServ.remove(idRole);

        return "role delete successfully";
    }

    @GetMapping("/get-by-id-role/{idRole}")
    public Role getByIdRole(@Validated @PathVariable Long idRole)
    {
        return this.roleServiceServ.getById(idRole);
    }
}
