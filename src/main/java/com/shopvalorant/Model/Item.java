package com.shopvalorant.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {
    private Long id;
    private String item_name;
    private int item_price;
    private String item_image_url;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    public Long getId() {
        return id;
    }

    @Column(name = "item_name")
    public String getItem_name() {
        return item_name;
    }

    @Column(name = "item_price")
    public int getItem_price() {
        return item_price;
    }

    @Column(name = "item_image_url")
    public String getItem_image_url() {
        return item_image_url;
    }

    public void setItem_image_url(String item_image_url) {
        this.item_image_url = item_image_url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item_price == item.item_price && id.equals(item.id) && Objects.equals(item_name, item.item_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item_name, item_price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", item_name='" + item_name + '\'' +
                ", item_price=" + item_price +
                '}';
    }
}
