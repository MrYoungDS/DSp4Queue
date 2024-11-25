package config;

import structures.LinkedQueue;
import structures.QueueInterface;

/**
 * The {@link Configuration} class is a set of static definitions
 * to choose from different implementations.
 *
 * @author jcollard, jddevaug
 */
public final class Configuration {

    // TONOTDO: You do not need to modify this class.
    // This is here for compatibility with the grocery simulator project.

    /**
     * Private constructor to prevent class instantiation.
     */
    private Configuration() {
    }

    /**
     * Returns the {@link QueueInterface} that you would like to be graded.
     *
     * @param <T>
     *            the type of data in the {@link QueueInterface}
     * @return the {@link QueueInterface} that you would like to be graded
     */
    public static <T> QueueInterface<T> getQueueImplementation() {
        return new LinkedQueue<>();
    }

}
