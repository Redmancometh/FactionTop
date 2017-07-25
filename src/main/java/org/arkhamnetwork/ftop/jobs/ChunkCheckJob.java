package org.arkhamnetwork.ftop.jobs;

import com.massivecraft.factions.Board;
import org.arkhamnetwork.ftop.FactionTop;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunkCheckJob implements Runnable {

    private FactionTop owner;

    private Chunk[] toCheck;

    private int chunkCount = 0;
    private final int maxCount = 5;

    private Map<String, Integer> factionTotals = new HashMap<>();

    public ChunkCheckJob(FactionTop owner) {
        this.owner = owner;
    }

    @Override
    public void run() {

        String worldName = "world"; //TODO replace with world from config.

        World world = owner.getBukkitPlugin().getServer().getWorld(worldName);

        toCheck = world.getLoadedChunks().clone();

        if (chunkCount < maxCount) {

            Chunk current = toCheck[chunkCount];

            for (int i = 0; i < current.getTileEntities().length; i++) {

                BlockState state = current.getTileEntities()[i];

                if (!owner.getPriceStore().priceable(state.getType())) {
                    continue;
                }

                if (state instanceof Chest) {

                    Chest chest = (Chest) state;
                    ChestCheckJob chestJob = new ChestCheckJob(this, chest);

                    chestJob.run(); //TODO run timer
                }
            }
        }
    }

    public void addTotal(String fName, Integer toAdd) {
        if (!factionTotals.containsKey(fName)) {
            factionTotals.put(fName, 0);
        }

        factionTotals.put(fName, factionTotals.get(fName) + toAdd);
    }
}
