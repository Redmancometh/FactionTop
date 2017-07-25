package org.arkhamnetwork.ftop.config.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FTopPriceConfig {

    private List<ValueEntry> prices;
}
