package fr.insy2s.commerce.shoponlineback.servicesDTO;

import fr.insy2s.commerce.shoponlineback.dtos.RoleDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.MapperAllDTO;
import fr.insy2s.commerce.shoponlineback.mappers.MapperAllDTOImpl;
import fr.insy2s.commerce.shoponlineback.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceDTO implements Webservices<RoleDTO> {

    private final RoleRepository roleRepository;

    private MapperAllDTO mapperAllDTO = new MapperAllDTOImpl();

    @Override
    public List<RoleDTO> all() {

        return this.mapperAllDTO.allDTOFromRole(this.roleRepository.findAll());
    }

    @Override
    public void add(RoleDTO e) {

    }

    @Override
    public RoleDTO update(Long id, RoleDTO e) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public RoleDTO getById(Long id) {
        return null;
    }
}
