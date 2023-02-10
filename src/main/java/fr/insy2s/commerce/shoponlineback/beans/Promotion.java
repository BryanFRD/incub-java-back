package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion")
    private Long idPromotion;

    @Column(name = "promo_start")
    private Date promoStart;

    @Column(name = "promo_end")
    private Date promoEnd;

    @Column(name = "discount")
    private Double discount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_product")
    @JsonIgnoreProperties({"promotions"})
    private Product product;




}
