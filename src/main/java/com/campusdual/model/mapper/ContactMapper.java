package com.campusdual.model.mapper;

import com.campusdual.model.Contact;
import com.campusdual.model.dto.ContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDto toDto(Contact contact);

    List<ContactDto> toDtoList(List<Contact> contactList);

    Contact toEntity(ContactDto contactDto);
}
