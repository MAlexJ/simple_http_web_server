package com.malexj.listener;

import com.malexj.model.entity.ModelEventEntity;
import com.malexj.model.event.ModelEvent;
import com.malexj.mapper.ModelEventEntityMapper;
import com.malexj.repository.ModelEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log
@Component
@RequiredArgsConstructor
public class GeneralApplicationEventListener {

    private final ModelEventRepository repository;
    private final ModelEventEntityMapper mapper;

    @Async
    @EventListener
    public void handleAndSaveModelEventToDb(ModelEvent event) {
        ModelEventEntity eventEntity = mapper.modelEventToModelEventEntity(event);
        eventEntity.setCreated(LocalDateTime.now());
        repository.save(eventEntity);
    }

}
