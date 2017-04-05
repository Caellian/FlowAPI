package hr.caellian.flow.network.structure;

import hr.caellian.flow.network.NetworkComponent;

import java.util.ArrayList;

/**
 * @author caellian
 *         Created on 23/07/16 at 01:15 CET.
 */
public interface StructureComponent extends NetworkComponent {
    /**
     * @return {@code true} if this structure component is a structure delegate,
     * {@code false} otherwise.
     */
    boolean isDelegate();

    boolean isStructureValid();

    ArrayList<NetworkComponent> getStructureComponents();

    default StructureCore getStructureCore() {
        for (NetworkComponent component : getStructureComponents()) {
            if (component instanceof StructureCore) {
                return (StructureCore) component;
            }
        }
        return null;
    }
}
