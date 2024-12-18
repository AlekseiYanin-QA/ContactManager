package org.example.contacts.facade;

import org.example.contacts.model.ContactDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ContactFacade {
    private final List<ContactDTO> contacts = new ArrayList<>();
    private final AtomicLong currentId = new AtomicLong(0);

    public ContactDTO saveContact(ContactDTO contactDTO) {
        contactDTO.setId((long) Math.toIntExact(currentId.incrementAndGet()));
        contacts.add(contactDTO);
        return contactDTO;
    }

    public List<ContactDTO> getAllContacts() {
        return new ArrayList<>(contacts); // Возвращаем копию списка
    }

    public Optional<ContactDTO> getContactById(long id) {
        return contacts.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst();
    }

    public Optional<ContactDTO> updateContact(long id, ContactDTO updatedContact) {
        return getContactById(id).map(contact -> {
            int index = contacts.indexOf(contact);
            if (index != -1) {
                updatedContact.setId(contact.getId()); // Сохраняем старый ID
                contacts.set(index, updatedContact);
            }
            return updatedContact;
        });
    }

    public boolean deleteContact(long id) {
        return contacts.removeIf(contact -> contact.getId() == id);
    }
}