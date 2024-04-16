package com.campusdual.model.dao;

import com.campusdual.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDao extends JpaRepository<Contact,Integer> {
}
