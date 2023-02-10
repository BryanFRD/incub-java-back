package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Promotion;
import fr.insy2s.commerce.shoponlineback.dtos.PromotionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "default", uses = {ProductMapper.class})
public interface PromotionMapper {

    @Mapping(target = "product.promotions", ignore = true)
    PromotionDTO formPromotion(Promotion promotion);


    @Mapping(target = "product.promotions", ignore = true)
    Promotion fromPromotionDTO(PromotionDTO promotionDTO);
}
