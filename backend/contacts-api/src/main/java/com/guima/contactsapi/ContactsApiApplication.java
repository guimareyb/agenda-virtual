package com.guima.contactsapi;

import com.guima.contactsapi.entity.Contact;
import com.guima.contactsapi.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ContactsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactsApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ContactRepository contactRepository){
        return args -> {
            List<Contact> contacts = Arrays.asList(
                    new Contact("Carlos", "carlos@gmail.com", LocalDateTime.now()),
                    new Contact("Juan", "Juan@gmail.com", LocalDateTime.now()),
                    new Contact("Marcelo", "Marcelo@gmail.com", LocalDateTime.now()),
                    new Contact("Luis", "Luis@gmail.com", LocalDateTime.now()),
                    new Contact("Erich", "Erich@gmail.com", LocalDateTime.now())
            );

            contactRepository.saveAll(contacts);
        };
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
