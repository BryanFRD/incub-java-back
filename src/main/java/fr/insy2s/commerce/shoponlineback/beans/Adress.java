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
@Table(name = "adresse")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_adresse")
    private Long id;

    @Column(name = "rue_complete")
    private String street;

    @Column(name = "ville")
    private String city;

    @Column(name = "code_postal", length = 5)
    private int codePostal;

    @Column(name = "statut_adresse")
    private String statut;

    @OneToMany(mappedBy = "adress")
    @JsonIgnoreProperties({"adresse", "utilisateur"})
    private List<Command> commands;
}
