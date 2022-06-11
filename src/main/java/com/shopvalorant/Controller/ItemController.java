package com.shopvalorant.Controller;

import com.shopvalorant.Exception.InternalServerError;
import com.shopvalorant.Model.Item;
import com.shopvalorant.Repositories.implantation.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(path = "/item/{itemId}", method = RequestMethod.GET)
    public String profile(Model model, @PathVariable String itemId) {
        try {
            Item item = itemRepository.read(Long.parseLong(itemId));
            model.addAttribute("item", item);
        } catch (NumberFormatException numberFormatException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }

        return "item-page";
    }

    @RequestMapping(path = "/item-page", method = RequestMethod.GET)
    public String itemPage() {
        return "addItem";
    }

    @PostMapping(path = "/add-item", produces = "text/plain")
    public ResponseEntity<String> registerUser(@ModelAttribute Item item) {
        itemRepository.save(item);

        return ResponseEntity.status(HttpStatus.OK).body("Item added");
    }
}
