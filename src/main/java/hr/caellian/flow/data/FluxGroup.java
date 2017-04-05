package hr.caellian.flow.data;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Flux Groups are a simple way of categorising different {@link FluxType Flux Types}
 * which helps with {@link Flux} conversion.
 *
 * Groups should be initialized using {@link #get(String, FluxGroup...)} method:<br/>
 * <code>
 *     FluxGroup example = get("example_group");
 * </code>
 *
 * @author Caellian
 * @since 1.0.0
 */
public class FluxGroup implements Predicate<FluxType> {
    /**
     * Map containing all registered {@link FluxGroup Flux Groups}.
     */
    public static final Map<String, FluxGroup> fluxGroups = new HashMap<>();

    /**
     * Name of this {@link FluxGroup Flux Group}.
     */
    private final String name;

    /**
     * {@link FluxType Flux Type} members of this {@link FluxGroup Flux Group}.
     */
    private final ArrayList<FluxType> members = new ArrayList<>();

    /**
     * List containing groups all members of which should be treated as if they
     * are a part of this group.
     */
    private final ArrayList<FluxGroup> compatibleGroups = new ArrayList<>();

    /**
     * Default constructor for a {@link FluxGroup Flux Group}.
     *
     * Instead of this constructor use {@link #get(String, FluxGroup...)} method.
     *
     * @param groupName name of the {@link FluxGroup Flux Group}.
     * @param compatibleGroups array containing groups all members of which
     *                         should be treated as if they are a part of this
     *                         group.
     */
    protected FluxGroup(String groupName, FluxGroup... compatibleGroups) {
        this.name = groupName;
        if (compatibleGroups.length > 0) {
            this.compatibleGroups.addAll(Arrays.asList(compatibleGroups));
        }
    }

    /**
     * This method should be used for creating and getting {@link FluxGroup Flux Groups}
     * as it makes sure {@link FluxGroup Flux Groups} with same names aren't
     * created.
     *
     * @param name name of {@link FluxGroup Flux Group} to find or create.
     * @param compatibleGroups list of compatible flux groups to use if creating
     *                         a new {@link FluxGroup Flux Group}.
     * @return registered {@link FluxGroup Flux Group} with argument name or a
     * newly constructed and registered one if it doesn't exist yet.
     */
    public static FluxGroup get(String name, FluxGroup... compatibleGroups) {
        return fluxGroups.computeIfAbsent(name, key -> new FluxGroup(key, compatibleGroups));
    }

    /**
     * @param name name of {@link FluxGroup Flux Group} to search for.
     * @return {@code true} if searched {@link FluxGroup Flux Group} exists,
     * {@code false} otherwise.
     */
    public static boolean exists(String name) {
        return fluxGroups.containsKey(name);
    }

    /**
     * This method collects all {@link FluxGroup Flux Groups} argument
     * {@link FluxType Flux Type} belongs to.
     *
     * @param powerType {@link FluxType Flux Type} to search for in groups.
     * @return list containing all groups {@link FluxType Flux Type} which contain
     * argument {@link FluxType Flux Type}.
     */
    public static ArrayList<FluxGroup> getForFluxType(FluxType powerType) {
        ArrayList<FluxGroup> result = new ArrayList<>();
        for (FluxGroup group : fluxGroups.values()) {
            result.addAll(group.members.stream().filter(member -> Objects.equals(member, powerType)).map(member -> group).collect(Collectors.toList()));
            for (FluxGroup compatibleGroup: group.compatibleGroups){
                result.addAll(compatibleGroup.members.stream().filter(member -> Objects.equals(member, powerType)).map(member -> compatibleGroup).collect(Collectors.toList()));
            }
        }
        return result;
    }

    /**
     * This method collects all {@link FluxGroup Flux Groups} argument
     * {@link FluxType Flux Type} belongs to.
     *
     * @param ID id of {@link FluxType Flux Type} to search for.
     * @return list containing all groups {@link FluxType Flux Type} which contain
     * argument {@link FluxType Flux Type} ID.
     */
    public static ArrayList<FluxGroup> getForFluxType(String ID) {
        ArrayList<FluxGroup> result = new ArrayList<>();
        for (FluxGroup group : fluxGroups.values()) {
            result.addAll(group.members.stream().filter(member -> Objects.equals(member.getID(), ID)).map(member -> group).collect(Collectors.toList()));
            for (FluxGroup compatibleGroup: group.compatibleGroups){
                result.addAll(compatibleGroup.members.stream().filter(member -> Objects.equals(member.getID(), ID)).map(member -> compatibleGroup).collect(Collectors.toList()));
            }
        }
        return result;
    }

    /**
     * Adds a {@link FluxType Flux Type} to this {@link FluxGroup Flux Group}.
     *
     * @param member {@link FluxType Flux Type} to add to this {@link FluxGroup Flux Group}.
     * @return {@code true} if the argument member has been successfully added
     * to this {@link FluxGroup Flux Group}, {@code false} otherwise.
     */
    public boolean addMember(FluxType member) {
        return members.add(member);
    }

    /**
     * Removes a {@link FluxType Flux Type} from this {@link FluxGroup Flux Group}.
     *
     * @param member {@link FluxType Flux Type} to remove from this {@link FluxGroup Flux Group}.
     * @return {@code true} if the argument member has been successfully removed
     * from this {@link FluxGroup Flux Group}, {@code false} otherwise.
     */
    public boolean removeMember(FluxType member) {
        return members.remove(member);
    }

    /**
     * This methods modifies compatibility between groups.
     *
     * @param other {@link FluxGroup Flux Group} to set compatibility for with
     *                                          this {@link FluxGroup Flux Group}.
     * @param compatible {@code true} if argument {@link FluxGroup Flux Group}
     *                               should be compatible with this one,
     *                               {@code false} otherwise.
     * @return {@code true} if successful, {@code false} otherwise.
     */
    public boolean setCompatibleWith(FluxGroup other, boolean compatible) {
        if (compatible) {
            return compatibleGroups.add(other);
        } else {
            return compatibleGroups.remove(other);
        }
    }

    /**
     * @param other a {@link FluxGroup Flux Group} to check for compatibility.
     * @return {@code true} if this {@link FluxGroup Flux Group} is compatible
     * with argument one, {@code false} otherwise.
     */
    public boolean isCompatibleWith(FluxGroup other) {
        return compatibleGroups.contains(other) || other.compatibleGroups.contains(this);
    }

    /**
     * @return name of this {@link FluxGroup Flux Group}.
     */
    public String getName() {
        return name;
    }

    /**
     * @param other a {@link FluxGroup Flux Group} to compare to this one.
     * @return {@code true} if this {@link FluxGroup Flux Group} is equal to
     * argument one, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof FluxGroup && this.getName().equals(((FluxGroup) other).getName());
    }

    /**
     * @param fluxType {@link FluxType Flux Type} to check for.
     * @return {@code true} if argument {@link FluxType Flux Type} is within this
     * {@link FluxGroup Flux Group}, {@code false} otherwise.
     */
    @Override
    public boolean test(FluxType fluxType) {
        return members.contains(fluxType);
    }
}
