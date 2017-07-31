package org.arkhamnetwork.ftop.jobs;

import com.massivecraft.factions.Factions;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.skin.SkinnableEntity;
import org.arkhamnetwork.ftop.FactionTop;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Matt on 2017-07-30.
 */
public class UpdatePedestalJob implements Runnable {

    private FactionTop owner;

    public UpdatePedestalJob(FactionTop owner) {
        this.owner = owner;
        owner.getServer().getScheduler().runTaskTimer(owner, this, 0L, 20*60);
    }

    @Override
    public void run() {

        LinkedHashMap<String, Integer> top = owner.getChunkCheckJob().getFactionTotals().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (ent1, ent2) -> ent1,
                        LinkedHashMap::new
                ));

        if (top.size() < 10) {
            //TODO print a notice
        }

        for (int i = 0; i <= 10; i++) {

            Location loc = owner.getPedestalConfigManager().getCurrentConfig().getPedestals().get(i).toLocation();

            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Top #" + i);

            SkinnableEntity skinnableEntity = (SkinnableEntity) npc.getEntity();
            skinnableEntity.setSkinName(Factions.getInstance().getFactionById((String) top.keySet().toArray()[i]).getFPlayerAdmin().getName());
            npc.spawn(loc); //TODO just update names maybe ? and remove old entities
        }
    }
}
