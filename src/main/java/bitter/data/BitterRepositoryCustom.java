package bitter.data;

import bitter.domain.Bitter;

import java.util.List;

/**
 * The interface Bitter repository custom.
 * @author mac
 */
public interface BitterRepositoryCustom {
    /**
     * Find follows by username list.
     *
     * @param currentBitter the current bitter
     * @return the list
     */
    List<Bitter> findFollowsByUsername(Bitter currentBitter);

    /**
     * Find fans by username list.
     *
     * @param currentBitter the current bitter
     * @return the list
     */
    List<Bitter> findFansByUsername(Bitter currentBitter);

    /**
     * Follow boolean.
     *
     * @param bitter        the bitter
     * @param currentBitter the current bitter
     * @return the boolean
     */
    Boolean follow(Bitter bitter, Bitter currentBitter);

    /**
     * Unfollow boolean.
     *
     * @param bitter        the bitter
     * @param currentBitter the current bitter
     * @return the boolean
     */
    Boolean unfollow(Bitter bitter, Bitter currentBitter);
}
