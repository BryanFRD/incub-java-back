package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordered")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordered")
    private Long idCommand;

    @Column(name = "ordered_date")
    private Instant commandDate;

    @Column(name = "order_status")
    private String statut;

    @Column(name = "delivery_ordered")
    private Instant deliveryDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_adress")
    @JsonIgnoreProperties({"ordersDelivered"})
    private Adress deliveryAdress;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_adress_1")
    @JsonIgnoreProperties({"ordersInvoiced"})
    private Adress billingAdress;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties({"commands"})
    private Account account;

    @OneToMany(mappedBy = "command", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"command"})
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "command", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"command", "product"})
    private List<OrderDetails> orderDetails;
}
