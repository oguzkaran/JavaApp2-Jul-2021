package org.csystem.app.api.invoice.repository;

import org.csystem.app.api.invoice.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author onder sahin
 */
public interface IInvoiceRepository extends JpaRepository<Invoice,Integer> {
}
