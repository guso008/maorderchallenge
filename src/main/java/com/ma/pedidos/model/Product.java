package com.ma.pedidos.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;

    @Column(name = "short_description", nullable = false, length = 50)
    String shortDescription;

    @Column(name = "large_description", length = 255)
    String largeDescription;

    @Column(name = "unit_price", nullable = false)
    Double unitPrice;

    @Column(name = "created_date", updatable = false, nullable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "delete_date")
    private Date deleteDate;

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLargeDescription() {
        return largeDescription;
    }

    public void setLargeDescription(String largeDescription) {
        this.largeDescription = largeDescription;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}
