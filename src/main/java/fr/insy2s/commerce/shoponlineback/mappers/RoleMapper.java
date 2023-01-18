package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.dtos.RoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {


    // For Role

    RoleDTO fromRole(Role role);

    Role fromRoleDTO(RoleDTO roleDTO);

    List<RoleDTO> allDTOFromRole(List<Role> roles);
}
