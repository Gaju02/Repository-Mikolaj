package inv.app01.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoicesRepository invoicesRepository;

    public List<Invoices> AllInvoices(){
        return invoicesRepository.findAll();
    }

    public Optional<Invoices> singleInvoice(String imdbId){
        return invoicesRepository.findInvoiceById(imdbId);
    }
}
