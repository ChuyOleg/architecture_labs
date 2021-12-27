package com.oleh.chui.service;

import com.oleh.chui.model.entity.Person;
import com.oleh.chui.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void create(Person person) {
        personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public Optional<Person> getPerson(final Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public List<Person> getPersons() {
        return personRepository.findAll();
    }
}
