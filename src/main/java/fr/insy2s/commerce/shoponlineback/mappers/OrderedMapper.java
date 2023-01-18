package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.dtos.OrderedDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderedMapper {

//    @Mapping(target = "deliveryAdress")
//    @Mapping(target = "billingAdress")
//    @Mapping(target = "account")
//    @Mapping(target = "invoices")
//    @Mapping(target = "orderDetails ")
    OrderedDTO fromOrdered(Ordered ordered);

//    @Mapping(target = "deliveryAdress")
//    @Mapping(target = "billingAdress")
//    @Mapping(target = "account")
//    @Mapping(target = "invoices")
//    @Mapping(target = "orderDetails ")
    Ordered fromOrderedDTO(OrderedDTO orderedDTO);

    List<OrderedDTO> allDTOfromOrdered(List<Ordered> ordereds);

}
