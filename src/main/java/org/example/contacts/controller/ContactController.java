package org.example.contacts.controller;

import org.example.contacts.facade.ContactFacade;
import org.example.contacts.model.ContactDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactFacade contactFacade;

    public ContactController(ContactFacade contactFacade) {
        this.contactFacade = contactFacade;
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactFacade.getAllContacts();
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long contactId) {
        return contactFacade.getContactById(contactId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO contactDTO) {
        ContactDTO createdContact = contactFacade.saveContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDTO> updateContact(
            @PathVariable Long contactId,
            @Valid @RequestBody ContactDTO updatedContact
    ) {
        return contactFacade.updateContact(contactId, updatedContact)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long contactId) {
        if (contactFacade.deleteContact(contactId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}