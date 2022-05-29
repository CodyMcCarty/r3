package io.cody.r3.service;

import io.cody.r3.domain.Message;
import io.cody.r3.model.MessageDTO;
import io.cody.r3.repos.MessageRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDTO> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(message -> mapToDTO(message, new MessageDTO()))
                .collect(Collectors.toList());
    }

    public MessageDTO get(final Long id) {
        return messageRepository.findById(id)
                .map(message -> mapToDTO(message, new MessageDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final MessageDTO messageDTO) {
        final Message message = new Message();
        mapToEntity(messageDTO, message);
        return messageRepository.save(message).getId();
    }

    public void update(final Long id, final MessageDTO messageDTO) {
        final Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(messageDTO, message);
        messageRepository.save(message);
    }

    public void delete(final Long id) {
        messageRepository.deleteById(id);
    }

    private MessageDTO mapToDTO(final Message message, final MessageDTO messageDTO) {
        messageDTO.setId(message.getId());
        messageDTO.setTo(message.getTo());
        messageDTO.setWhoFrom(message.getWhoFrom());
        messageDTO.setBody(message.getBody());
        return messageDTO;
    }

    private Message mapToEntity(final MessageDTO messageDTO, final Message message) {
        message.setTo(messageDTO.getTo());
        message.setWhoFrom(messageDTO.getWhoFrom());
        message.setBody(messageDTO.getBody());
        return message;
    }

}
