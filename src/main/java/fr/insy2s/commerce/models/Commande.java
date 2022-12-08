package fr.insy2s.Commerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date_commande;

    private String statut_commande;

    private Date date_livraison;

    @ManyToOne
    private Utliisateur utilisateur;

    @OneToOne
    private Facture facture;

    @ManyToOne
    private Adresse adresse;

    @OneToMany(mappedBy = "commande")
    private List<Panier> paniers;

}
