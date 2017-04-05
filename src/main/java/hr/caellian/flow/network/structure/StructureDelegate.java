package hr.caellian.flow.network.structure;

/**
 * @author caellian
 *         Created on 23/07/16 at 01:14 CET.
 */
public interface StructureDelegate extends StructureComponent {
    @Override
    default boolean isDelegate() {
        return true;
    }

    @Override
    default boolean isStructureValid() {
        StructureCore core = getStructureCore();
        return core != null && core.isStructureValid();
    }
}
