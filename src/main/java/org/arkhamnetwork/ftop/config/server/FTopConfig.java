package org.arkhamnetwork.ftop.config.server;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.arkhamnetwork.ftop.data.FactionEntry;

import java.util.List;

@Data
@NoArgsConstructor
public class FTopConfig {

    private List<FactionEntry> rankings;
}
