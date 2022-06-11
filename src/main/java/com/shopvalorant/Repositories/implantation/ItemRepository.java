package com.shopvalorant.Repositories.implantation;

import com.shopvalorant.Exception.InternalServerError;
import com.shopvalorant.Exception.ItemNotFoundException;
import com.shopvalorant.Model.Item;
import com.shopvalorant.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class ItemRepository implements GenericRepository<Item> {

    @Autowired
    private GeneralRepository<Item> generalDAO;

    @Override
    public Item save(Item item) {
        try {
            return generalDAO.save(item);
        } catch (Exception e){
            throw new InternalServerError("Can't save item, 'couse something went wrong in db\n" + e.getMessage());
        }
    }

    @Override
    public Item read(Long id) {
        Item item;

        try {
            generalDAO.seteClass(Item.class);
            item = generalDAO.read(id);
        } catch (NoResultException noResultException) {
            throw new ItemNotFoundException("Item not found");
        } catch (Exception e){
            throw new InternalServerError("Can't read user, 'couse something went wrong in db\n" + e.getMessage());
        }

        return item;
    }

    @Override
    public Item update(Item t) {
        return null;
    }

    @Override
    public void delete(Item t) {

    }
}
