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
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "reset_token")
    private String resetToken;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_role")
    @JsonIgnoreProperties({"users"})
    private Role role;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "deliveryAdress", "billingAdress"})
    private List<Command> commands;
}
