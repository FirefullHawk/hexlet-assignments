package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ContactDTO created(@RequestBody ContactCreateDTO createDTO) {
        Contact contact = new Contact();

        contact.setFirstName(createDTO.getFirstName());
        contact.setLastName(createDTO.getLastName());
        contact.setPhone(createDTO.getPhone());

        contactRepository.save(contact);

        ContactDTO newContactDTO = new ContactDTO();

        newContactDTO.setId(contact.getId());
        newContactDTO.setFirstName(contact.getFirstName());
        newContactDTO.setLastName(contact.getLastName());
        newContactDTO.setPhone(contact.getPhone());
        newContactDTO.setUpdatedAt(contact.getUpdatedAt());
        newContactDTO.setCreatedAt(contact.getCreatedAt());

        return newContactDTO;
    }
    // END
}
