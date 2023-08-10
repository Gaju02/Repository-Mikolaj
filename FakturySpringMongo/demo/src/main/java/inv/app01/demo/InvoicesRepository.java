package inv.app01.demo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoicesRepository extends MongoRepository<Invoices, ObjectId> {
    Optional<Invoices> findInvoiceById(String id);
}

