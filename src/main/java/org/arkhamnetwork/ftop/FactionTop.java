package org.arkhamnetwork.ftop;

import com.redmancometh.redcore.RedPlugin;
import com.redmancometh.redcore.config.ConfigManager;
import com.redmancometh.redcore.mediators.ObjectManager;
import org.arkhamnetwork.ftop.config.server.PedestalConfig;
import org.arkhamnetwork.ftop.config.user.FTopPriceConfig;
import org.arkhamnetwork.ftop.pedestal.PedestalCommand;
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
    private ConfigManager<PedestalConfig> pedestalConfigManager;



    private PriceStore priceStore;


    public void onEnable() {
        this.priceConfigManager = new ConfigManager("prices.json", FTopPriceConfig.class);
        this.pedestalConfigManager = new ConfigManager<>("pedestals.json", PedestalConfig.class);

        this.factionManager = new FactionManager();

        priceConfigManager.init(this);
        pedestalConfigManager.init(this);

        this.enable();

        //LOAD TO PRICE STORE

        priceStore = new PriceStore();

        getCommand("pedestal").setExecutor(new PedestalCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.disable();
    }

    public void reloadConfig() {

        priceConfigManager.init(this);
        pedestalConfigManager.init(this);
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

    public static PedestalConfig getPedestalConfig() {
        return getInstance().getPedestalConfigManager().getCurrentConfig();
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

    public ConfigManager<PedestalConfig> getPedestalConfigManager() {
        return pedestalConfigManager;
    }

    public PriceStore getPriceStore() {
        return priceStore;
    }
}
