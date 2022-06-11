package com.shopvalorant.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_items")
public class UserItems implements Serializable {
    private Long id;
    private Item item;
    private Date date_bought;
    private User owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "date_bought")
    public Date getDate_bought() {
        return date_bought;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    public User getOwner() {
        return owner;
    }

    @OneToOne
    @JoinColumn(name = "item_id")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate_bought(Date date_bought) {
        this.date_bought = date_bought;
    }

    @Override
    public String toString() {
        return "UserItems{" +
                "id=" + id +
                ", item=" + item.getItem_name() +
                ", date_bought=" + date_bought +
                ", owner=" + owner +
                '}';
    }
}
