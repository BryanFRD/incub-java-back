package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.dtos.PromotionDTO;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.PromotionNotFoundException;
import fr.insy2s.commerce.shoponlineback.services.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping-online")
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping("/no-role/all-promotion")
    public ResponseEntity<Page<PromotionDTO>> allPromotion(Pageable pageable)
    {
        return ResponseEntity.ok(this.promotionService.all(pageable));
    }

    @GetMapping("/no-role/get-by-id-promotion/{idPromotion}")
    public ResponseEntity<PromotionDTO> getByIdPromotion(@Valid @PathVariable Long idPromotion)
    {
        return this.promotionService.getById(idPromotion)
                .map(promotionDTO -> new ResponseEntity<>(promotionDTO, HttpStatus.OK))
                .orElseThrow(() -> new PromotionNotFoundException("Promotion with id " +idPromotion+ " was not found"));


    }
}
