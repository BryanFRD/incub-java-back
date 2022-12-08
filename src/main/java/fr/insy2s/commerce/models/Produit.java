package fr.insy2s.Commerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label_produit;

    private String reference_produit;

    private String description_produit;

    private double prix_ttc;

    private Integer stock_produit;

    @OneToMany(mappedBy = "produit")
    private List<Image> images;

    @ManyToOne
    private Categorie categorie;

}
