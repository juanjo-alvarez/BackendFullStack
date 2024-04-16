package com.campusdual.service;

import com.campusdual.api.IContactService;
import com.campusdual.model.Contact;
import com.campusdual.model.dao.ContactDao;
import com.campusdual.model.dto.ContactDto;
import com.campusdual.model.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ContactService")
@Lazy
public class ContactService implements IContactService {

    @Autowired
    private ContactDao ContactDao;

    @Override
    public ContactDto queryContact(ContactDto ContactDto) {
        Contact contact = ContactDao.getReferenceById(ContactMapper.INSTANCE.toEntity(ContactDto).getId());
        return ContactMapper.INSTANCE.toDto(contact);
    }

    @Override
    public List<ContactDto> queryAllContacts() {
        return ContactMapper.INSTANCE.toDtoList(ContactDao.findAll());
    }

    @Override
    public int insertContact(ContactDto ContactDto) {
        Contact Contact = ContactMapper.INSTANCE.toEntity(ContactDto);
        ContactDao.saveAndFlush(Contact);
        return Contact.getId();
    }

    @Override
    public int updateContact(ContactDto ContactDto) {
        return insertContact(ContactDto);
    }

    @Override
    public int deleteContact(ContactDto ContactDto) {
        //TODO probar otras implementaciones
        int id = ContactDto.getId();
        Contact Contact = ContactMapper.INSTANCE.toEntity(ContactDto);
        ContactDao.delete(Contact);
        return id;
    }
}
