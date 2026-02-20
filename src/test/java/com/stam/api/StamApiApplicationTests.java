package com.stam.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Exclure DataSourceAutoConfiguration et HibernateJpaAutoConfiguration
// pour éviter les problèmes de configuration manquante de base de données lors des tests
@SpringBootTest(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
class StamApiApplicationTests {

    @Test
    void contextLoads() {
    }

}