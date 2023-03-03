package com.chadwick.GoBankDB.unit.Repository;

import com.chadwick.GoBankDB.entity.Customer;
import com.chadwick.GoBankDB.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeAll
    void setUp() {
        try{
            String stringTooLong = Files.readString(Path.of("src/test/java/com/chadwick/GoBankDB/data.txt"));
            JsonArray array = JsonParser.parseString(stringTooLong).getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                String obj = array.get(i).toString();
                ObjectMapper mapper = new ObjectMapper();
                Customer user = mapper.readValue(obj, Customer.class);
                customerRepository.save(user);
            }
        }catch (Exception e){
            throw new Error(e);
        }
    }

    @Test
    void itShouldCheckThatAll10UsersWereSaved(){
        try {
            Iterable<Customer> usersIterable = customerRepository.findAll();
            ArrayList<Customer> customerArrayList = new ArrayList<>((Collection) usersIterable);
            assertThat(customerArrayList.size()).isEqualTo(10);
            assertThat(customerArrayList.get(0).getEmail()).isNotNull();
        }catch (Exception e){
            throw new Error(e);
        }
    }
}