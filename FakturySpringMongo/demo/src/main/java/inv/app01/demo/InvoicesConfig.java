package inv.app01.demo;

import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InvoicesConfig {
    @Bean
    CommandLineRunner commandLineRunner(InvoicesRepository repository){
        return args -> {
            Invoices fv1 = new Invoices(
                    new ObjectId().toString(),
                    "20/03/2023",
                    "Maluta",
                    "Farmaz",
                    "szt.",
                    "100",
                    "3",
                    "23",
                    "karta"
            );

            Invoices fv2 = new Invoices(
                    new ObjectId().toString(),
                    "11/02/2023",
                    "Lemex",
                    "Grapi",
                    "szt.",
                    "100",
                    "4",
                    "8",
                    "gotowka"
            );

            repository.saveAll(
                    List.of(fv1,fv2)
            );
        };
    }
}
