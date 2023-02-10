package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address", nullable = false, length = 50)
    private Long id;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "postal_code", length = 5, nullable = false)
    private int codePostal;

    @Column(name = "statut_adress", length = 50, nullable = true) // TODO voir à quoi ca sert
    private String statut;

    @ToString.Exclude
    @OneToMany(mappedBy = "deliveryAdress", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"deliveryAdress", "billingAdress", "account"})
    private List<Ordered> ordersDelivered;

    @ToString.Exclude
    @OneToMany(mappedBy = "billingAdress", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"deliveryAdress", "billingAdress", "account"})
    private List<Ordered> ordersInvoiced;


}
