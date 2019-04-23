package com.sic777.restful.spring.spi.impl;

import com.sic777.spring.laucher.SpringWebContainerLauncher;
import com.sic777.restful.base.counter.IAllUriSPI;

import java.util.Set;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class AllUriSpiImpl implements IAllUriSPI {
    @Override
    public Set<String> uris() {
        return SpringWebContainerLauncher.instance().getAllUri();
    }
}
