package web.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;


import org.springframework.context.annotation.Configuration;

@Configuration
public class Util {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                Map<String, String> properties = new HashMap<>();
                properties.put("javax.persistence.jdbc.url", "jdbc:mysql://korzened.beget.tech:3306/korzened_kata?useSSL=false");
                properties.put("javax.persistence.jdbc.user", "korzened_kata");
                properties.put("javax.persistence.jdbc.password", "Q1S2C3q1w2e3r4t5y6");
                properties.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
                properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                properties.put("hibernate.show_sql", "true");
                properties.put("hibernate.hbm2ddl.auto", "update");

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
