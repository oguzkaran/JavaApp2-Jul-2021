package org.csystem.app.api.invoice.service;

import org.csystem.app.api.invoice.domain.Invoice;
import org.csystem.app.api.invoice.repository.IInvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;
import static org.csystem.util.data.DatabaseUtil.doWorkForService;

/**
 * @author onder sahin
 */
@Service
public class InvoiceService {

    private IInvoiceRepository invoiceRepository;

    public InvoiceService(IInvoiceRepository invoiceRepository)
    {
        this.invoiceRepository = invoiceRepository;
    }

    private Invoice saveInvoiceCallback(Invoice invoice)
    {
        return invoiceRepository.save(invoice);
    }

    private Invoice updateInvoiceCallback(Invoice invoice)
    {
        return invoiceRepository.save(invoice);
    }

    private Invoice deleteInvoiceCallback(Invoice invoice)
    {
        invoiceRepository.deleteById(invoice.getId());
        return invoice;
    }

    public Invoice saveInvoice(Invoice invoice)
    {
        return doWorkForService(() -> saveInvoiceCallback(invoice), "InvoiceService.saveInvoice");
    }

    public Invoice updateInvoice(Invoice invoice)
    {
        return doWorkForService(() -> updateInvoiceCallback(invoice), "InvoiceService.updateInvoice");
    }


    public Invoice deleteInvoice(Invoice invoice)
    {
        return doWorkForService(() -> deleteInvoiceCallback(invoice), "InvoiceService.deleteInvoice");
    }

    public Optional<Invoice> findInvoiceById(int id)
    {
        return doWorkForRepository(() -> invoiceRepository.findById(id), "InvoiceService.findInvoiceById");
    }



}
