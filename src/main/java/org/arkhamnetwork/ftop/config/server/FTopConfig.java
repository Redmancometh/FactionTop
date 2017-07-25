package org.arkhamnetwork.ftop.config.server;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FTopConfig {

    private List<FactionEntry> rankings;
}
