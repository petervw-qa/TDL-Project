package com.qa.application.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.application.persistence.domain.ListItem;

@Repository
public interface ListItemRepo extends JpaRepository<ListItem, Long>{

}
