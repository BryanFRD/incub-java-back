package fr.insy2s.commerce.shoponlineback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @EmbeddedId
    private KeyOfOrderDetails keyOfOrderDetails;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId("idProduct")
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    @JsonIgnoreProperties({"orderDetails"})
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId("idCommand")
    @JoinColumn(name = "id_ordered", insertable = false, updatable = false)
    @JsonIgnoreProperties({"orderDetails"})
    private Command command;
}