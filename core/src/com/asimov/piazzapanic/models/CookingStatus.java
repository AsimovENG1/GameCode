package com.asimov.piazzapanic.models;

/**
 * The status of a cooking station.
 */
public enum CookingStatus {
    /**
     * The cooking station is available to be used.
     */
    available,

    /**
     * An ingredient is on the cooking station, but has not been prepared.
     */
    cooking,

    /**
     * An ingredient is on the cooking station and is ready to be grabbed.
     */
    complete
}
