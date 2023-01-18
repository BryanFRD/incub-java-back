package fr.insy2s.commerce.shoponlineback.controllerDTO;

import fr.insy2s.commerce.shoponlineback.dtos.RoleDTO;
import fr.insy2s.commerce.shoponlineback.servicesDTO.RoleServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-dto/role")
@RequiredArgsConstructor
public class RoleControllerDTO {

    private final RoleServiceDTO roleServiceDTO;

    @GetMapping("/all-role-dto")
    public List<RoleDTO> allRoleDTO()
    {
        return this.roleServiceDTO.all();
    }

}
