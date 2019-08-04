package com.symulakr.service.user.service.db.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LiquibaseMigrationTest.TestConfiguration.class)
public class LiquibaseMigrationTest {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Test
   void shouldSuccessfullyQueryTableCreatedByLiquibase() throws Exception {
      jdbcTemplate.execute("select count(*) from user");
   }

   @Configuration
   @EnableAutoConfiguration
   public static class TestConfiguration {
   }
}
