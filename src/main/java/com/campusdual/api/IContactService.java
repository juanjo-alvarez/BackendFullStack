package com.campusdual.api;

import com.campusdual.model.dto.ContactDto;

import java.util.List;

public interface IContactService {
    ContactDto queryContact(ContactDto ContactDto);

    List<ContactDto> queryAllContacts();

    int insertContact(ContactDto ContactDto);

    int updateContact(ContactDto ContactDto);

    int deleteContact(ContactDto ContactDto);
}
