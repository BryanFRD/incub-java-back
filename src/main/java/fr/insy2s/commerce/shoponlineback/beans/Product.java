package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "ref_product")
    private String refProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "price_ttc")
    private Double priceTTC;

    @Column(name = "product_inventory")
    private Long productInventory;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_category")
    @JsonIgnoreProperties({"products"})
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"product"})
    private List<Picture> pictures;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"product", "ordered"})
    public List<OrderDetails> orderDetails;
}
