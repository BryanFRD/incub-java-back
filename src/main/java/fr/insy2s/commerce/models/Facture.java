package fr.insy2s.Commerce.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom_facture;

    private Date date_facturation;

    @OneToOne
    private Commande commande;

    @ManyToOne
    private Adresse adresse;

}
