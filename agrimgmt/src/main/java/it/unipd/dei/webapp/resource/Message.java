package it.unipd.dei.webapp.resource;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Represents a message or an error message.
 * 
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public class Message extends Resource {

	private final String message;
	private final String errorCode;
	private final String errorDetails;
	private final boolean isError;


	/**
	 * Creates an error message.
	 * @param message the text of the message
	 * @param errorCode the error code of the message
	 * @param errorDetails the text error details of the message
	 */
	public Message(final String message, final String errorCode, final String errorDetails) {
		this.message = message;
		this.errorCode = errorCode;
		this.errorDetails = errorDetails;
		this.isError = true;
	}

	/**
	 * Creates a generic message.
	 * @param message the text of the message
	 */
	public Message(final String message) {
		this.message = message;
		this.errorCode = null;
		this.errorDetails = null;
		this.isError = false;
	}

	/**
	 * @return the message text.
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @return the error code
	 */
	public final String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the error details
	 */
	public final String getErrorDetails() {
		return errorDetails;
	}

	/**
	 * @return true if an error has occurred, false otherwise
	 */
	public final boolean getIsError() {
		return isError;
	}

	/**
	 * Retrieve a {@code Message} and write it through JSON representation.
	 *
	 * @param out the output stream containing the JSON document.
	 *
	 * @throws IOException if something goes wrong while parsing.
	 */
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("message");

		jg.writeStartObject();

		jg.writeStringField("message", message);

		if (errorCode != null) {
			jg.writeStringField("error-code", errorCode);
		}

		if (errorDetails != null) {
			jg.writeStringField("error-details", errorDetails);
		}

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}
}
