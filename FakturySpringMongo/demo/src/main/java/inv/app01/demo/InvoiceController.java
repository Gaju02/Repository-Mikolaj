package inv.app01.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoices>> getAllInvoices(){
        return new ResponseEntity<List<Invoices>>(invoiceService.AllInvoices(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Invoices>> getSingleInvoice(@PathVariable String id){
        return new ResponseEntity<Optional<Invoices>>(invoiceService.singleInvoice(id),HttpStatus.OK);
    }

    @GetMapping("/root")
    public String toString(){
        return "Hello in root!";
    }

}
