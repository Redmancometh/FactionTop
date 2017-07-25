package org.arkhamnetwork.ftop.jobs;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import lombok.Getter;
import org.arkhamnetwork.ftop.FactionTop;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestCheckJob implements Runnable {

    private ChunkCheckJob master;

    private Chest chest;

    @Getter
    private int total = 0;

    public ChestCheckJob(ChunkCheckJob master, Chest chest) {
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

        Faction factionAt = Board.getInstance().getFactionAt(new FLocation(chest.getLocation()));

        if (factionAt == null) return;

        master.addTotal(factionAt.getId(), total);
    }
}
