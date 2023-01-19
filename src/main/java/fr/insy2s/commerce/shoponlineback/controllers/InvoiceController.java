package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Invoice;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.InvoiceService_serv;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService_serv invoiceServiceServ;

    @GetMapping("/all-invoice")
    public List<Invoice> allInvoice()
    {
        return this.invoiceServiceServ.all();
    }

    @PostMapping("/add-invoice")
    public String addInvoice(@Validated @RequestBody Invoice invoice)
    {
        this.invoiceServiceServ.add(invoice);

        return "invoice successfully add";
    }

    @PutMapping("/update-invoice/{idInvoice}")
    public String updateInvoice(@Validated @PathVariable Long idInvoice, @RequestBody Invoice invoice)
    {
        this.invoiceServiceServ.update(idInvoice, invoice);

        return "invoice update complete successfully";
    }

    @DeleteMapping("/remove-invoice/{idInvoice}")
    public String removeInvoice(@Validated @PathVariable Long idInvoice)
    {
        this.invoiceServiceServ.remove(idInvoice);

        return "invoice delete successfully";
    }

    @GetMapping("/get-by-id-invoice/{idInvoice}")
    public Invoice getByIdInvoice(@Validated @PathVariable Long idInvoice)
    {
        return this.invoiceServiceServ.getById(idInvoice);
    }
}
