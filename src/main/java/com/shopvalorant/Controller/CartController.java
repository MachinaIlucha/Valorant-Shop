package com.shopvalorant.Controller;

import com.shopvalorant.Exception.InternalServerError;
import com.shopvalorant.Model.Item;
import com.shopvalorant.Repositories.implantation.ItemRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    ItemRepository itemRepository;

    @PostMapping(path = "/add-to-cart/{itemId}")
    public String addItemToCart(HttpSession session, @PathVariable String itemId) {
        if (session.getAttribute("login") == null)
            return "login-page";

        List<Item> cartItems;

        if (session.getAttribute("cartItems") == null)
            cartItems = new ArrayList<>();
        else {
            try {
                cartItems = (ArrayList<Item>) session.getAttribute("cartItems");
            } catch (Exception e){
                throw new InternalServerError("Can't cast `cartItems` to ArrayList");
            }
        }

        Item item = itemRepository.read(Long.parseLong(itemId));
        cartItems.add(item);

        session.setAttribute("cartItems", cartItems);

        return "index";
    }

    @GetMapping(path = "/cart-page")
    public String cartPage(HttpSession session, Model model) {
        if (session.getAttribute("login") == null)
            return "login-page";

        List<Item> cartItems = (List<Item>) session.getAttribute("cartItems");

        model.addAttribute("cartItems", cartItems);


        return "cart-page";
    }
}
