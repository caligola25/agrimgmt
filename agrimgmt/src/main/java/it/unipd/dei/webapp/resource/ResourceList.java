package it.unipd.dei.webapp.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.*;

/**
 * Represents a list of {@link Resource} objects.
 *
 * @param <T> the type of the actual class extending {@code Resource}.
 */
public final class ResourceList<T extends Resource> extends Resource {

    /**
     * The list of {@code Resource}s.
     */
    private final Iterable<T> list;

    /**
     * Creates a list of {@code Resource}s.
     *
     * @param list the list of {@code Resource}s.
     */
    public ResourceList(final Iterable<T> list) {
        this.list = list;
    }

    /**
     * Returns a JSON representation of the ResourceList into the given OutputStream.
     *
     * @param out the stream to which the JSON representation of the {@code Resource} has to be written.
     * @throws IOException if something goes wrong during the parsing.
     */
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("it.unipd.dei.webapp.resource-list");

        jg.writeStartArray();

        jg.flush();

        boolean firstElement = true;

        for (final Resource r : list) {

            if (firstElement) {
                r.toJSON(out);
                jg.flush();

                firstElement = false;
            } else {
                jg.writeRaw(',');
                jg.flush();

                r.toJSON(out);
                jg.flush();
            }
        }

        jg.writeEndArray();

        jg.writeEndObject();

        jg.flush();
    }
}