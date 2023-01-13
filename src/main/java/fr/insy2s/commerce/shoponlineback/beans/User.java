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
@Table(name = "utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "nom")
    private String name;

    @Column(name = "prenom")
    private String firstName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "reset_token")
    private String resetToken;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ref_role")
    @JsonIgnoreProperties({"users"})
    private Role role;

/*    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"utilisateur", "adresse"})
    private List<Command> commands;*/
}
