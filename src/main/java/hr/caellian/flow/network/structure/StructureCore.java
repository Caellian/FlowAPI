package hr.caellian.flow.network.structure;

/**
 * Structure core is a {@link StructureComponent}
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface StructureCore extends StructureComponent {
    /**
     * @return {@code false} as a structure core is not a structure delegate.
     */
    @Override
    default boolean isDelegate() {
        return false;
    }
}
