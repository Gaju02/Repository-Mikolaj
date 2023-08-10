package inv.app01.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies-db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoices {
    @Id
    private String id;

    private String numer_faktury;
    private String kupujący;
    private String sprzedający;
    private String jednostka;
    private String cenaBrutto;
    private String ilosc;
    private String VAT;
    private String sposob_zaplaty;
}
