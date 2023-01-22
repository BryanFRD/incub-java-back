package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Invoice;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService_serv {

    private final InvoiceRepository invoiceRepository;

    public List<Invoice> all() {
        return this.invoiceRepository.findAll();
    }

    public void add(Invoice e) {

        e.setRefInvoice(UUID.randomUUID().toString());

        this.invoiceRepository.save(e);
    }

    public Invoice update(Long id, Invoice e) {
        return this.invoiceRepository.findById(id)
                .map(p -> {
                    p.setRefInvoice(UUID.randomUUID().toString());
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if (p.getBillingDate() != null)
                        p.setBillingDate(e.getBillingDate());
                    if (p.getOrdered() != null)
                        p.setOrdered(e.getOrdered());
                    return this.invoiceRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("sorry this invoice is not found"));
    }

    public void remove(Long id) {

        Invoice invoice = this.invoiceRepository.findById(id).get();

        if (invoice != null)
            this.invoiceRepository.delete(invoice);
    }

    public Invoice getById(Long id) {
        return this.invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("not found id invoice"));
    }
}
