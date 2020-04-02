package com.smolenskyi.sweater.repository;

import com.smolenskyi.sweater.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

    Page<Message> findAll(Pageable pegeable);

    Page<Message> findByTag(String tag, Pageable pegeable);
}
