package it.unipd.dei.webapp.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Represents the data about the credentials.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class Credential extends Resource{
    private final String username;
    private final String password;
    private final UUID employeeId;

    /**
     * Creates a new object Credential
     * @param username username of the employee
     * @param password password of the employee
     * @param employeeId UUID of the employee
     */
    public Credential(final String username,final String password,final UUID employeeId) {
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
    }

    /**
     * @return the username
     */
    public final String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @return the employeeID
     */
    public final UUID getEmployeeId() {
        return employeeId;
    }

    /**
     * Retrieve a {@code Credential} and write it through JSON representation.
     *
     * @param out the output stream containing the JSON document.
     *
     * @throws IOException if something goes wrong while parsing.
     */
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("credential");

        jg.writeStartObject();

        jg.writeStringField("username", username);

        jg.writeStringField("password", password);

        jg.writeStringField("employeeId", employeeId.toString());

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

    /**
     * Creates a {@code Credential} from its JSON representation.
     *
     * @param in the input stream containing the JSON document.
     *
     * @return the {@code Credential} created from the JSON representation.
     *
     * @throws IOException if something goes wrong while parsing.
     */
    public static Credential fromJSON(final InputStream in) throws IOException {

        // the fields read from JSON
        String jUsername = null;
        String jPassword = null;
        UUID jEmployeeId = null;

        final JsonParser jp = JSON_FACTORY.createParser(in);

        // while we are not on the start of an element or the element is not
        // a token element, advance to the next element (if any)
        while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !"credential".equals(jp.getCurrentName())) {

            // there are no more events
            if (jp.nextToken() == null) {
                throw new IOException("Unable to parse JSON: no credential object found.");
            }
        }

        while (jp.nextToken() != JsonToken.END_OBJECT) {

            if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                switch (jp.getCurrentName()) {
                    case "employeeId":
                        jp.nextToken();
                        jEmployeeId = UUID.fromString(jp.getText());
                        break;
                    case "username":
                        jp.nextToken();
                        jUsername = jp.getText();
                        break;
                    case "password":
                        jp.nextToken();
                        jPassword = jp.getText();
                        break;
                }
            }
        }

        return new Credential(jUsername, jPassword, jEmployeeId);
    }

}