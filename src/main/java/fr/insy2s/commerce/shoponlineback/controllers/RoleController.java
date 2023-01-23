package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.dtos.RoleDTO;
import fr.insy2s.commerce.shoponlineback.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-dto/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /*@GetMapping("/all-role-dto")
    public List<RoleDTO> allRoleDTO()
    {
        return this.roleService.all();
    }*/

}
