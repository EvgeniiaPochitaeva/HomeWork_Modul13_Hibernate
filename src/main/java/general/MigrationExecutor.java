package general;

import general.client.ClientCrudService;
import org.flywaydb.core.Flyway;

import java.io.IOException;

public class MigrationExecutor{

    public static void main(String[] args) throws IOException {
        // Створюємо екземпляр класу PropertiesFileReader
        String url = new PropertiesFileReader().getPath();

        Flyway flyway = Flyway .configure()
                .dataSource(url, null, null)
                .load();
        flyway.migrate();


    }
}
