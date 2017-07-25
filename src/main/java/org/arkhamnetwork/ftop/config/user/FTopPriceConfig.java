package org.arkhamnetwork.ftop.config.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.arkhamnetwork.ftop.data.FactionEntry;
import org.arkhamnetwork.ftop.data.ValueEntry;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class FTopPriceConfig {

    private List<ValueEntry> prices;
}
