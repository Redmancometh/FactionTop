package org.arkhamnetwork.ftop.config.server;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Matt on 2017-07-29.
 */
@Data
public class PedestalEntry {

    private String world;
    private int x,y,z;

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public PedestalEntry fromLocation(Location location) {
        this.world = location.getWorld().getName().trim();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        return this;
    }
}
