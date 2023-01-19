package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @Column(name = "ref_account", length = 250, nullable = true) // TODO mettre en nullable false après changement en BDD
    @NotNull
    private String refAccount;

    @Column(name = "name", nullable = false, length = 100)
    @NotNull
    @NotBlank(message = "sorry name is required")
    private String name;

    @Column(name = "first_name", nullable = false, length = 100)
    @NotNull
    @NotBlank(message = "sorry firstname is required")
    private String firstName;

    @Column(name = "password", nullable = false, length = 250)
    @NotNull
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    @NotNull
    private String email;

    @Column(name = "reset_token", nullable = true, length = 100)
    private String resetToken;

    /*@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_role")
    @JsonIgnoreProperties({"accounts"})
    private Role role;*/


    @ManyToMany
    @Column(name = "id_role", nullable = false)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "id_account"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    List<Role> roles;

    @ToString.Exclude
    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "deliveryAdress", "billingAdress"})
    private List<Ordered> ordereds;
}
