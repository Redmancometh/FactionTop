package org.arkhamnetwork.ftop.prices;

import org.arkhamnetwork.ftop.FactionTop;
import org.bukkit.Material;

import java.util.HashMap;

public class PriceStore {

    private HashMap<Material, Integer> itemPriceStore;

    public PriceStore() {
        this.itemPriceStore = new HashMap<>();

        FactionTop.getPriceConfig().getPrices().forEach(entry -> {
            itemPriceStore.putIfAbsent(entry.getMaterial(), entry.getValue());
        });
    }

    public Integer getPrice(Material item) {
        Integer price = itemPriceStore.get(item);

        return price == null ? 0 : price;
    }

    public boolean priceable(Material mat) {
        return itemPriceStore.containsKey(mat);
    }
}
