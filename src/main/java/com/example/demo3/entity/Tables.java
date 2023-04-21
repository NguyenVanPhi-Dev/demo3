package com.example.demo3.entity;

import javax.persistence.*;

@Entity
@Table(name = "tables")
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long status;
    private String description;

    public Tables(String name, Long status, String description) {
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Tables(Long id, String name, Long status, String description) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Tables() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
