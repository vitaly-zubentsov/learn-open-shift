package org.example.addressbook.controllers;

import org.example.addressbook.model.AddressBook;
import org.example.addressbook.repository.AddressBookRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1")
public class AddressBookController {

    private final AddressBookRepository repository;

    public AddressBookController(AddressBookRepository repository) {
        this.repository = repository;
    }
    @GetMapping("test")
    public String getTest() {
        return "Hello world";
    }

    @GetMapping("reactive-test")
    public Mono<String> getReactiveTest(){
        return Mono.just("Hello reactive world");
    }

    @GetMapping("addressbooks")
    public Flux<AddressBook> getAddressBooks(){
        return repository.findAll();
    }

    @PostMapping("addressbooks")
    public Mono<AddressBook> save(@RequestBody AddressBook addressBook) {
       return repository.save(addressBook);
    }
}
