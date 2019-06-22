package net.codetojoy.service;

import java.util.*;

import net.codetojoy.cache.*;
import net.codetojoy.pojo.*;

public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public Item getItem(int id) {
        return itemDAO.getItem(id);
    }

    public void list() {
        List<Item> items = itemDAO.getItems();

        for (Item item : items) {
            System.out.println(item.toString());
        }
    }

    public void clear() {
        itemDAO.clear();
    }
}
