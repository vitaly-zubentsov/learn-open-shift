package org.example.addressbook.repository;

import org.example.addressbook.model.AddressBook;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends ReactiveCrudRepository<AddressBook, Long> {
}
