package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.insy2s.commerce.shoponlineback.enums.OrderedStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordered")
@ToString
public class Ordered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordered", nullable = false)
    private Long idOrdered;

    @Column(name = "ref_ordered", nullable = false, length = 250)
    private String refOrdered;

    @Column(name = "ordered_date",nullable = false)
    private Instant orderedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",nullable = false, length = 50)
    private OrderedStatus statut;

    @Column(name = "delivery_ordered", nullable = false)
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

    @ToString.Exclude
    @OneToMany(mappedBy = "ordered", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"ordered"})
    private List<Invoice> invoices;

    @ToString.Exclude
    @OneToMany(mappedBy = "ordered", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"ordered", "product"})
    private List<OrderDetails> orderDetails;
}
