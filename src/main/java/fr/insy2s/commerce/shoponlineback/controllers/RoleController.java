package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/all-role")
    public List<Role> AllRole()
    {
        return this.roleService.all();
    }

    @PostMapping("/add-role")
    public String addRole(@Validated @RequestBody Role role)
    {
        this.roleService.add(role);

        return "rol successfully add";
    }

    @PutMapping("/update-role/{idRole}")
    public String updateRole(@Validated @PathVariable Long idRole, @RequestBody Role role)
    {
        this.roleService.update(idRole, role);

        return "role update complete successfully";
    }

    @DeleteMapping("/remove-role/{idRole}")
    public String removeRole(@Validated @PathVariable Long idRole)
    {
        this.roleService.remove(idRole);

        return "role delete successfully";
    }

    @GetMapping("/get-by-id-role/{idRole}")
    public Role getByIdRole(@Validated @PathVariable Long idRole)
    {
        return this.roleService.getById(idRole);
    }
}
