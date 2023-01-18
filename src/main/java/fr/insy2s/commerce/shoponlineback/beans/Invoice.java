package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Long id;

    @Column(name = "ref_invoice")
    private String refInvoice;

    @Column(name = "name")
    private String name;

    @Column(name = "billing_date")
    private Date billingDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_ordered")
    @JsonIgnoreProperties({"invoices"})
    private Ordered ordered;
}
