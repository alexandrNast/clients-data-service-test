package ru.softlab.zikova.efr.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.softlab.zikova.efr.model.ClientEntity;
import ru.softlab.zikova.efr.repositories.ClientRepository;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Сервис для работы с клиентами
 *
 * @author zykova
 * @since 21.04.2021
 */
@Service
public class ClientService {
    @Resource
    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    /**
     * Возвращает сущность
     * @param id идентификатор
     * @return сущность
     */
    public ClientEntity findById(long id) {
        ClientEntity entity = repository.findOne(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

    /**
     * Добавить/обновить сущность
     * @param entity сущность
     * @return entity
     */
    public ClientEntity save(ClientEntity entity) {
        return repository.save(entity);
    }

    /**
     * Удалить сущность
     * @param id идентификатор
     */
    public void remove(Long id) {
        repository.deleteClientWithProducts(id);
    }

    public Page<ClientEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
