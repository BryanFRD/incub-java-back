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
@Table(name = "adress")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adress")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code", length = 5)
    private int codePostal;

    @Column(name = "statut_adress")
    private String statut;

    @OneToMany(mappedBy = "deliveryAdress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"deliveryAdress", "billingAdress", "account"})
    private List<Command> ordersDelivered;

    @OneToMany(mappedBy = "billingAdress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"deliveryAdress", "billingAdress", "account"})
    private List<Command> ordersInvoiced;


}
