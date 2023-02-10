package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Promotion;
import fr.insy2s.commerce.shoponlineback.dtos.PromotionDTO;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.PromotionNotFoundException;
import fr.insy2s.commerce.shoponlineback.exceptions.generic_exception.WebservicesGenericServiceException;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapper;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapperImpl;
import fr.insy2s.commerce.shoponlineback.mappers.PromotionMapper;
import fr.insy2s.commerce.shoponlineback.mappers.PromotionMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionService implements Webservices<PromotionDTO, WebservicesGenericServiceException> {

    private final PromotionRepository promotionRepository;

    private final PromotionMapper promotionMapper = new PromotionMapperImpl();

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Override
    public Page<PromotionDTO> all(Pageable pageable) {
        return this.promotionRepository.findAll(pageable)
                .map(this.promotionMapper::formPromotion);
    }

    @Override
    public void add(PromotionDTO e) throws WebservicesGenericServiceException {

        this.promotionRepository.save(this.promotionMapper.fromPromotionDTO(e));
    }

    @Override
    public PromotionDTO update(Long id, PromotionDTO e) throws WebservicesGenericServiceException {
        return this.promotionMapper.formPromotion(this.promotionRepository.findById(id)
                .map(p ->{
                    if (p.getPromoStart() != null)
                        p.setPromoStart(e.getPromoStart());
                    if (p.getPromoEnd() != null)
                        p.setPromoEnd(e.getPromoEnd());
                    if (p.getDiscount() != null)
                        p.setDiscount(e.getDiscount());
                    if (p.getProduct() != null)
                        p.setProduct(this.productMapper.fromProductDTO(e.getProduct()));
                    return this.promotionRepository.save(p);
                }).orElseThrow(() -> new PromotionNotFoundException("this promo is not found"))
        );
    }

    @Override
    public void remove(Long id) throws WebservicesGenericServiceException {

        Optional<Promotion> promotion = this.promotionRepository.findById(id);

        if (promotion.isEmpty())
            throw new PromotionNotFoundException("not found ");

        this.promotionRepository.deleteById(id);
    }

    @Override
    public Optional<PromotionDTO> getById(Long id) throws WebservicesGenericServiceException {
        return this.promotionRepository.findById(id)
                .map(this.promotionMapper::formPromotion);
    }
}
