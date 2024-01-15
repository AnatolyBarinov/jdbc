package org.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SCRIPT_FILE_PATH = "schema.sql";

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProductName(String customerName) {
        String sql = "SELECT product_name FROM ORDERS WHERE customer_id IN (SELECT id FROM CUSTOMERS WHERE LOWER(name) = :name)";
        Map<String, String> paramMap = Collections.singletonMap("name", customerName.toLowerCase());
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }
}