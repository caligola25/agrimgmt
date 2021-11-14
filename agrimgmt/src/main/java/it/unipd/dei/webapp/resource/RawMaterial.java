package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents the data about the raw materials.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public class RawMaterial {

    /**
     * The material UUID
     */
    private final UUID materialId;

    /**
     * The name of the material
     */
    private final String materialName;

    /**
     * Creates a new material
     *
     * @param materialId
     *            the material UUID
     * @param materialName
     *            the name of the material
     */
    public RawMaterial(final UUID materialId, final String materialName) {
        this.materialId = materialId;
        this.materialName = materialName;
    }

    /**
     * Returns the material UUID.
     *
     * @return the material UUID.
     */
    public final UUID getMaterialId() {
        return materialId;
    }

    /**
     * Returns the name of the material.
     *
     * @return the name of the material.
     */
    public final String getMaterialName() {
        return materialName;
    }
}