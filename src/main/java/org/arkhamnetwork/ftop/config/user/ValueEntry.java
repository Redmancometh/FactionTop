package org.arkhamnetwork.ftop.config.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Material;

@Data
@NoArgsConstructor
public class ValueEntry {

    private Material material;
    private int value;
}
