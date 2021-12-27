package com.oleh.chui.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.oleh.chui.model.entity.Person;
import com.oleh.chui.service.PersonService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonQuery implements GraphQLQueryResolver {

    private final PersonService personService;

    public PersonQuery(PersonService personService) {
        this.personService = personService;
    }

    public Optional<Person> person(final Long id) {
        return personService.getPerson(id);
    }

    public List<Person> persons() {
        return personService.getPersons();
    }
}
