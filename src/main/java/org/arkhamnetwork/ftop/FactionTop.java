package org.arkhamnetwork.ftop;

import com.redmancometh.redcore.RedPlugin;
import com.redmancometh.redcore.config.ConfigManager;
import com.redmancometh.redcore.mediators.ObjectManager;
import org.arkhamnetwork.ftop.config.server.FTopConfig;
import org.arkhamnetwork.ftop.config.user.FTopPriceConfig;
import org.arkhamnetwork.ftop.prices.PriceStore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FactionTop extends JavaPlugin implements RedPlugin {

    private SessionFactory factory;

    private List<Class> classList = new CopyOnWriteArrayList();

    private FactionManager factionManager;


    /*
        User Config
     */

    private ConfigManager<FTopPriceConfig> priceConfigManager;



    /*
        Server Config
     */
    private ConfigManager<FTopConfig> topConfigManager;



    private PriceStore priceStore;


    public void onEnable() {
        this.priceConfigManager = new ConfigManager("prices.json", FTopPriceConfig.class);
        this.topConfigManager = new ConfigManager<>("factions.json", FTopConfig.class);

        this.factionManager = new FactionManager();

        priceConfigManager.init(this);
        topConfigManager.init(this);

        this.enable();

        //LOAD TO PRICE STORE

        priceStore = new PriceStore();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.disable();
    }

    public void reloadConfig() {

        priceConfigManager.init(this);
        topConfigManager.init(this);
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public static FTopPriceConfig getPriceConfig() {
        return getInstance().getPriceConfigManager().getCurrentConfig();
    }

    public static FTopConfig getTopConfig() {
        return getInstance().getTopConfigManager().getCurrentConfig();
    }


    public FactionManager getFactionManager() {
        return factionManager;
    }

    public void setFactionManager(FactionManager factionManager) {
        this.factionManager = factionManager;
    }

    public static FactionTop getInstance() {
        return (FactionTop) Bukkit.getPluginManager().getPlugin("FactionTop");
    }

    @Override
    public JavaPlugin getBukkitPlugin() {
        return this;
    }

    @Override
    public ObjectManager getManager() {
        return null;
    }

    @Override
    public SessionFactory getInternalFactory() {
        return factory;
    }

    @Override
    public List<Class> getMappedClasses() {
        return classList;
    }

    @Override
    public void setInternalFactory(SessionFactory newFactory) {
        this.factory = newFactory;
    }

    public ConfigManager<FTopPriceConfig> getPriceConfigManager() {
        return priceConfigManager;
    }

    public ConfigManager<FTopConfig> getTopConfigManager() {
        return topConfigManager;
    }

    public PriceStore getPriceStore() {
        return priceStore;
    }
}
