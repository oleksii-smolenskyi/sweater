package com.smolenskyi.sweater.service;

import com.smolenskyi.sweater.domain.User;
import com.smolenskyi.sweater.domain.dto.MessageDto;
import com.smolenskyi.sweater.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user) {
        if(filter!=null && !filter.isEmpty())
            return messageRepo.findByTag(filter, pageable, user);
        else
            return messageRepo.findAll(pageable, user);
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable, author, currentUser);
    }
}