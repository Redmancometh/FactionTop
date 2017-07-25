package org.arkhamnetwork.ftop.prices;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PriceStore {

    private HashMap<Material, Integer> itemPriceStore;

    public PriceStore() {
        this.itemPriceStore = new HashMap<>();
    }

    public Integer getPrice(Material item) {
        Integer price = itemPriceStore.get(item);

        return price == null ? 0 : price;
    }

    public boolean priceable(Material mat) {
        return itemPriceStore.containsKey(mat);
    }
}
