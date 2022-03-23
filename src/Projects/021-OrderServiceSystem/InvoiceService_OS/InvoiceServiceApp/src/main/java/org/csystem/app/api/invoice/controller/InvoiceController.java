package org.csystem.app.api.invoice.controller;

import org.csystem.app.api.invoice.controller.dto.InvoiceSaveRequestDTO;
import org.csystem.app.api.invoice.controller.mapper.InvoiceMapper;
import org.csystem.app.api.invoice.service.InvoiceService;
import org.csystem.app.pdb.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;

/**
 * @author onder sahin
 */
@RestController
@RequestMapping("api/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;
    private InvoiceMapper invoiceMapper;

    public InvoiceController(InvoiceService invoiceService, InvoiceMapper invoiceMapper)
    {
        this.invoiceService = invoiceService;
        this.invoiceMapper = invoiceMapper;
    }

    @GetMapping("test")
    public String test(){
        return "Test";
    }

    @PostMapping
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> saveInvoice(@RequestBody InvoiceSaveRequestDTO invoiceSaveRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(invoiceService.saveInvoice(invoiceMapper.toEntity(invoiceSaveRequestDTO)).getId())
        );
    }

    @PutMapping("/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> updateInvoice(@PathVariable int id, @RequestBody InvoiceSaveRequestDTO invoiceUpdateRequestDTO)
    {
        return invoiceService.findInvoiceById(id)
                .map(invoice -> ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResponse<>(invoiceService.updateInvoice(invoiceMapper.updateEntity(invoiceUpdateRequestDTO,invoice)).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }


    @DeleteMapping("/{id}")
    @RolesAllowed({"SYSTEM_ADMIN","ADMIN"})
    public ResponseEntity<ApiResponse<Integer>> deleteInvoice(@PathVariable int id)
    {
        return invoiceService.findInvoiceById(id)
                .map(invoice -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                        new ApiResponse<>(invoiceService.deleteInvoice(invoice).getId()))
                ).orElseThrow(EntityNotFoundException::new);
    }

}
