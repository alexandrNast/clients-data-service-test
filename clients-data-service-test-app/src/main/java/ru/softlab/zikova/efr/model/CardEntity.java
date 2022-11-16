package ru.softlab.zikova.efr.model;

import javax.persistence.*;

@Entity
@Table(name="cards")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer number;
    @Column
    private String clientName;

//    @Column
//    private ClientEntity clientEntity;


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public ClientEntity getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(ClientEntity clientId) {
//        this.clientId = clientId;
//    }
}
