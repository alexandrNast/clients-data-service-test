/*
 * clients-data-service-test
 * Тестовый API
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ru.softlab.zikova.efr.exchange.model;

import com.fasterxml.jackson.annotation.*;
import java.util.Objects;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

/**
 * Данные клиента
 */
@SuppressWarnings("all")
@Validated
public class Client {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("surname")
    private String surname = null;


    /**
     * Создает пустой экземпляр класса
     */
    public Client() {}

    /**
     * Создает экземпляр класса
     * 
     * @param name Имя клиента
     * @param surname Фамилия клиента
     */
    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    /**
     * Имя клиента
     *
     * @return Имя клиента
     */
    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Фамилия клиента
     *
     * @return Фамилия клиента
     */
    


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(this.name, client.name) &&
                Objects.equals(this.surname, client.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Client {\n");
        
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

