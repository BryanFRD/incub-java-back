package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Invoice;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService implements Webservices<Invoice> {

    private final InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> all() {
        return this.invoiceRepository.findAll();
    }

    @Override
    public void add(Invoice e) {

        this.invoiceRepository.save(e);
    }

    @Override
    public Invoice update(Long id, Invoice e) {
        return this.invoiceRepository.findById(id)
                .map(p -> {
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if (p.getBillingDate() != null)
                        p.setBillingDate(e.getBillingDate());
                    if (p.getCommand() != null)
                        p.setCommand(e.getCommand());
                    return this.invoiceRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("sorry this invoice is not found"));
    }

    @Override
    public void remove(Long id) {

        Invoice invoice = this.invoiceRepository.findById(id).get();

        if (invoice != null)
            this.invoiceRepository.delete(invoice);
    }

    @Override
    public Invoice getById(Long id) {
        return this.invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("not found id invoice"));
    }
}