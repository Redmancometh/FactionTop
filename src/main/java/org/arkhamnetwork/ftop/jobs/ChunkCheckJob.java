package org.arkhamnetwork.ftop.jobs;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import org.arkhamnetwork.ftop.FactionTop;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class ChunkCheckJob implements Runnable {

    private FactionTop owner;

    private List<Chunk> toCheck;

    private int chunkCount = 0;
    private final int maxCount = 5;

    private BukkitTask task;

    private Map<String, Integer> factionTotals = new HashMap<>();

    public ChunkCheckJob(FactionTop owner) {
        this.owner = owner;
        this.task = Bukkit.getScheduler().runTaskTimer(owner.getBukkitPlugin(), this, 0L, 60*20L);
    }

    @Override
    public void run() {

        String worldName = "world"; //TODO replace with world from config.

        World world = owner.getBukkitPlugin().getServer().getWorld(worldName);

        toCheck = Arrays.asList(world.getLoadedChunks().clone());

        if (chunkCount < maxCount) {

            Chunk current = toCheck.get(chunkCount);

            for (int i = 0; i < current.getTileEntities().length; i++) {

                BlockState state = current.getTileEntities()[i];

                if (!owner.getPriceStore().priceable(state.getType())) {
                    continue;
                }

                if (state instanceof Chest) {

                    Chest chest = (Chest) state;
                    ChestCheckJob chestJob = new ChestCheckJob(this, chest);


                    //Chest job only needs to run once, then it is done.
                    chestJob.run();
                }
                else {

                    Faction facAt = Board.getInstance().getFactionAt(new FLocation(state.getLocation()));

                    if (facAt == null) {
                        continue;
                    }

                    addTotal(facAt.getId(), owner.getPriceStore().getPrice(state.getType()));
                }
            }
            chunkCount++;
            //TODO remove from toCheck array

            for (int i = 0; i < chunkCount; i++) {
                toCheck.remove(0);
            }

            chunkCount = 0;
        }
    }

    public void addTotal(String fName, Integer toAdd) {
        if (!factionTotals.containsKey(fName)) {
            factionTotals.put(fName, 0);
        }

        factionTotals.put(fName, factionTotals.get(fName) + toAdd);
    }
}
