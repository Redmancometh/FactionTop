package org.arkhamnetwork.ftop.jobs;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Matt on 2017-07-30.
 */
public class UpdatePedestalJob implements Runnable {

    private JavaPlugin owner;

    public UpdatePedestalJob(JavaPlugin owner) {
        this.owner = owner;
        owner.getServer().getScheduler().runTaskTimer(owner, this, 0L, 20*60);
    }

    @Override
    public void run() {


    }
}
