package fr.insy2s.commerce.shoponlineback.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.beans.Address;
import fr.insy2s.commerce.shoponlineback.beans.Invoice;
import fr.insy2s.commerce.shoponlineback.beans.OrderDetails;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderedDTO {

    private Long id;

    private String refOrdered;

    private Instant orderedDate;

    private String statut;

    private Instant deliveryDate;

    @JsonIgnore
    private Address deliveryAdress;
    @JsonIgnore
    private Address billingAdress;
    @JsonIgnore
    private Account account;
    @JsonIgnore
    private List<Invoice> invoices;
    @JsonIgnore
    private List<OrderDetails> orderDetails;

}
