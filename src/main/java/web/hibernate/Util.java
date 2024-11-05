package web.hibernate;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class util {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                Map<String, String> properties = new HashMap<>();
                properties.put(AvailableSettings.URL, "jdbc:mysql://korzened.beget.tech:3306/korzened_kata?useSSL=false");
                properties.put(AvailableSettings.USER, "korzened_kata");
                properties.put(AvailableSettings.PASS, "Q1S2C3q1w2e3r4t5y6");
                properties.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
                properties.put(AvailableSettings.SHOW_SQL, "true");
                properties.put(AvailableSettings.HBM2DDL_AUTO, "update");

                entityManagerFactory = Persistence.createEntityManagerFactory("default", properties);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExceptionInInitializerError("EntityManagerFactory initialization failed." + e);
            }
        }
        return entityManagerFactory;
    }

    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
