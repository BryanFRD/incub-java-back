package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "ref_account")
    @NotNull
    private String refAccount;

    @Column(name = "name")
    @NotNull
    @NotBlank(message = "sorry name is required")
    private String name;

    @Column(name = "first_name")
    @NotNull
    @NotBlank(message = "sorry firstname is required")
    private String firstName;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "email")
    @NotEmpty
    @NotNull
    private String email;

    @Column(name = "reset_token")
    private String resetToken;

    /*@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_role")
    @JsonIgnoreProperties({"accounts"})
    private Role role;*/

    @ManyToMany
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "id_account"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    List<Role> roles;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "deliveryAdress", "billingAdress"})
    private List<Ordered> ordereds;
}
