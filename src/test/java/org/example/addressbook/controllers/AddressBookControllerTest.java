package org.example.addressbook.controllers;

import org.example.addressbook.model.AddressBook;
import org.example.addressbook.repository.AddressBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class AddressBookControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    AddressBookRepository repository;

    @Test
    void contextLoads() {
        assertNotNull(webTestClient);
    }

    @Test
    void testFindAll() {
        webTestClient.get()
                .uri("/api/v1/addressbooks")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("[{\"id\":-2,\"firstName\":\"Oleg\",\"lastName\":\"Olegovich\",\"birthday\":\"1900-03-11\"}," +
                        "{\"id\":-1,\"firstName\":\"Petr\",\"lastName\":\"Petrov\",\"birthday\":\"1980-03-11\"}]");
    }

    @Test
    void testSave() {
        AddressBook addressBook = new AddressBook(null, "Ivan", "Ivanov", "+71325143670", LocalDate.parse("2000-01-03"));

        Long sizeBefore = repository.findAll().count().block();

        webTestClient.post()
                .uri("/api/v1/addressbooks")
                .bodyValue(addressBook)
                .header("Content-type", "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("{\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"phone\":\"+71325143670\",\"birthday\":\"2000-01-03\"}");

    Long sizeAfter = repository.findAll().count().block();

    assertTrue(sizeBefore < sizeAfter);

    }
}