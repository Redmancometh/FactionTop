package org.arkhamnetwork.ftop.jobs;

import org.arkhamnetwork.ftop.FactionTop;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestCalcJob implements Runnable {

    private ChunkCheckJob master;
    private Chest chest;


    private int total = 0;

    public ChestCalcJob(ChunkCheckJob master, Chest chest) {
        this.master = master;
        this.chest = chest;
    }


    @Override
    public void run() {

        Inventory inventory = chest.getBlockInventory();

        for(ItemStack item : inventory) {

            if (!FactionTop.getInstance().getPriceStore().priceable(item.getType())) {
                continue;
            }


        }
    }
}
