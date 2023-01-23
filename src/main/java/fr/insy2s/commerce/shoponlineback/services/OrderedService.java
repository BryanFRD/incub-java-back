package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Address;
import fr.insy2s.commerce.shoponlineback.beans.Invoice;
import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.dtos.OrderedDTO;
import fr.insy2s.commerce.shoponlineback.enums.OrderedStatus;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.OrderedNotFoundException;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.ProductNotFoundException;
import fr.insy2s.commerce.shoponlineback.exceptions.generic_exception.WebservicesGenericServiceException;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.*;
import fr.insy2s.commerce.shoponlineback.repositories.OrderedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderedService implements Webservices<OrderedDTO, WebservicesGenericServiceException> {

    private final OrderedRepository orderedRepository;
    private final OrderedMapper orderedMapper = new OrderedMapperImpl();

    private final AddressMapper addressMapper = new AddressMapperImpl();

//    private final InvoiceMapper invoiceMapper = new InvoiceMapperImpl();

    private final UuidService uuidService;

//    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<OrderedDTO> all(Pageable pageable) {

        return this.orderedRepository.findAll(pageable)
                .map(this.orderedMapper::fromOrdered);
    }

    @Override
    public void add(OrderedDTO e) {
        e.setRefOrdered(this.uuidService.generateUuid());
        if (e.getStatut() == null)
            e.setStatut(OrderedStatus.CREATED);
        Ordered ordered = this.orderedMapper.fromOrderedDTO(e);
        this.orderedRepository.save(ordered);
    }

    @Override
    public OrderedDTO update(Long id, OrderedDTO e) {
        return this.orderedMapper.fromOrdered(this.orderedRepository.findById(id)
                .map(p-> {
//                    p.setRefOrdered(this.uuidService.generateUuid());
                    if(p.getOrderedDate() != null){
                        p.setOrderedDate(e.getOrderedDate());
                    }
                    if (p.getStatut() != null){
                        p.setStatut(e.getStatut());
                    }
                    if(p.getDeliveryDate() != null){
                        p.setDeliveryDate(e.getDeliveryDate());
                    }

                    if (p.getDeliveryAdress() != null)
                    {
                        Address deliveryAddress = this.addressMapper.fromAddressDTO(e.getDeliveryAdress());
                        p.setDeliveryAdress(deliveryAddress);
                    }

                    if (p.getBillingAdress() != null)
                    {
                        Address billingAddress = this.addressMapper.fromAddressDTO(e.getBillingAdress());
                        p.setBillingAdress(billingAddress);
                    }
/*                    if (p.getInvoices() != null)
                    {
                        List<Invoice> invoices = e.getInvoices().stream().map(this.invoiceMapper::fromInvoiceDTO).collect(Collectors.toList());
                        p.setInvoices(invoices);
                    }*/
                    return this.orderedRepository.save(p);
                }).orElseThrow(() -> new ProductNotFoundException("Ordered with id " +id+ " was not found")));
    }


    @Override
    public void remove(Long id) {

        Optional<Ordered> ordered = this.orderedRepository.findById(id);

        if (ordered.isEmpty())
            throw new OrderedNotFoundException("Ordered with id " +id+ " was not found");
        this.orderedRepository.deleteById(id);
    }

    @Override
    public Optional<OrderedDTO> getById(Long id) {

        return this.orderedRepository.findById(id)
                .map(this.orderedMapper::fromOrdered)
                .map(Optional::of)
                .orElseThrow(() -> new OrderedNotFoundException("Ordered with id " +id+ " was not found"));
    }

//    public void updateOrderedStatus()
//    {
//        this.jdbcTemplate.execute("update ordered set status ='CONFIRMED' where order_status = 'livrer'");
//    }

}
