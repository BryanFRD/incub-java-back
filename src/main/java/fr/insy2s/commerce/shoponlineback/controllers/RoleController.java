package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/listesRole")
    public List<Role> listes_role()
    {
        return this.roleService.listes();
    }

    @PostMapping("/addRole")
    public String add_role(@Validated @RequestBody Role role)
    {
        this.roleService.add(role);

        return "role ajouter avec succèss";
    }

    @PutMapping("/updateRole/{ref_role}")
    public String update_role(@Validated @PathVariable Long ref_role, @RequestBody Role role)
    {
        this.roleService.update(ref_role, role);

        return "role modifier avec succèss";
    }

    @DeleteMapping("/removeRole/{ref_role}")
    public String remove_role(@Validated @PathVariable Long ref_role)
    {
        this.roleService.remove(ref_role);

        return "role supprimer avec succcès";
    }

    @GetMapping("/getByIdRole/{ref_role}")
    public Role getById_role(@Validated @PathVariable Long ref_role)
    {
        return this.roleService.getById(ref_role);
    }
}
