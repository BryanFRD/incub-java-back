package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.dtos.AddressDTO;
import fr.insy2s.commerce.shoponlineback.dtos.OrderedDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderedMapper {

    @Mapping(source= "idOrdered", target="id")
    OrderedDTO fromOrdered(Ordered ordered);

    @Mapping(source= "id", target="idOrdered")
    Ordered fromOrderedDTO(OrderedDTO orderedDTO);

    List<OrderedDTO> allDTOfromOrdered(List<Ordered> ordereds);

}
