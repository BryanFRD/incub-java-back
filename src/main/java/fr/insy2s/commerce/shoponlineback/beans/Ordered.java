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
public class Ordered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordered")
    private Long idOrdered;

    @Column(name = "ref_ordered")
    private String refOrdered;

    @Column(name = "ordered_date")
    private Instant orderedDate;

    @Column(name = "order_status")
    private String statut;

    @Column(name = "delivery_ordered")
    private Instant deliveryDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_adress_delivery")
    @JsonIgnoreProperties({"ordersDelivered"})
    private Address deliveryAdress;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_adress_invoiced")
    @JsonIgnoreProperties({"ordersInvoiced"})
    private Address billingAdress;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties({"ordereds"})
    private Account account;

    @OneToMany(mappedBy = "ordered", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"ordered"})
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "ordered", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"ordered", "product"})
    private List<OrderDetails> orderDetails;
}
