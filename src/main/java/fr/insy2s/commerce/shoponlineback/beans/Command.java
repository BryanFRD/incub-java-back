package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commande")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commande")
    private Long id;

    @Column(name = "date_commande")
    private Instant commandDate;

    @Column(name = "statut_commande")
    private String statut;

    @Column(name = "date_livraison")
    private Instant deliveryDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ref_adresse")
    @JsonIgnoreProperties({"commandes"})
    private Adress adress;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties({"commandes"})
    private User user;
}
