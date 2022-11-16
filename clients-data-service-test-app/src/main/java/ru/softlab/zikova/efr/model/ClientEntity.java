package ru.softlab.zikova.efr.model;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность клиента
 *
 * @author zykova
 * @since 21.04.2021
 */
@Entity
@Table(name="clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "is_active")
    private Boolean isActive;

    public ClientEntity(Long id, String name, String surname, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
    }

    public ClientEntity( String name, String surname, Boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
    }

    public ClientEntity() {
    }


    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
