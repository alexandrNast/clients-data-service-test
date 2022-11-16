package ru.softlab.zikova.efr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.zikova.efr.model.ClientEntity;

import java.util.List;

/**
 * Интерфейс для работы со справочником клиентов
 *
 * @author zykova
 * @since 21.04.2021
 */
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    /**
     * Поиск клиента по фамилии
     * @param surname фамилия
     * @return список клиентов
     */
    List<ClientEntity> findBySurname(String surname);

    /**
     * Поиск активных клиентов
     * @return активные клиенты
     */
    @Query(value = "select e from ClientEntity e where e.isActive = true")
    List<ClientEntity> findActiveClients();

    //fixme поправить на аннотации
    /**
     * Удаление клиентов с продуктами
     */
    @Transactional
    @Modifying
    @Query(value = "delete from ClientEntity e where e.id = :id")
    void deleteClientWithProducts(@Param("id") Long id);
}
