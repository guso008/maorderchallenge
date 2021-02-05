package com.ma.pedidos.model;

import com.ma.pedidos.model.enums.OrderStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "order_head")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue
    UUID id;

    String address;

    String email;

    String phone;

    @Column(name = "order_time")
    LocalTime orderTime;

    @Column(name = "created_date", updatable = false, nullable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "total_amount")
    Double totalAmount;

    @Column(name = "apply_discount")
    Boolean applyDiscount;

    OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> details;

    public Order() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getApplyDiscount() {
        return applyDiscount;
    }

    public void setApplyDiscount(Boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}
