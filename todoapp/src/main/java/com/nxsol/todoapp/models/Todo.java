package com.nxsol.todoapp.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="todo")
public @Data  class Todo {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    private String description;
    
    private Boolean completed = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Date createdAt;

    public Todo() {
        super();
    }

    public Todo(String title) {
        this.title = title;
    }

   

    @Override
    public String toString() {
        return String.format(
                "Todo[id=%s, title='%s', completed='%s']",
                id, title, completed);
    }
}