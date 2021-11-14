package it.unipd.dei.webapp.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Represents the data about the employee.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class Employee extends Resource {
	private final UUID employeeId;
	private final String surname;
	private final String name;
	private final int nOperation;
	private final float salary;
	private final String role;

	/** Creates an object Employee
	 * @param employeeId the UUID of the employee
	 * @param surname the surname of the employee
	 * @param name the name of the employee
	 * @param nOperation the number of operations assigned to the employee
	 * @param salary the salary of the employee
	 * @param role the role of the employee
	 */
	public Employee(final UUID employeeId,final String surname,final String name, int nOperation,final float salary,final String role) {
		this.employeeId = employeeId;
		this.surname = surname;
		this.name = name;
		this.nOperation = nOperation;
		this.salary = salary;
		this.role = role;
	}

	/**
	 * @return the employeeID
	 */
	public UUID getEmployeeId() {
		return employeeId;
	}

	/**
	 * @return the employee's surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the employee's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the employee's nOperation
	 */
	public int getnOperation() {
		return nOperation;
	}

	/**
	 * @return the employee's salary
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * @return the employee's role
	 */
	public String getRole() {
		return role;
	}


	/**
	 * Retrieve a {@code Employee} and write it through JSON representation.
	 *
	 * @param out the output stream containing the JSON document.
	 *
	 * @throws IOException if something goes wrong while parsing.
	 */
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("employee");

		jg.writeStartObject();

		jg.writeStringField("employeeId", employeeId.toString());

		jg.writeStringField("surname", surname);

		jg.writeStringField("name", name);

		jg.writeNumberField("nOperation", nOperation);

		jg.writeNumberField("salary", salary);

		jg.writeStringField("role", role);

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}

	/**
	 * Creates a {@code Employee} from its JSON representation.
	 *
	 * @param in the input stream containing the JSON document.
	 *
	 * @return the {@code Employee} created from the JSON representation.
	 *
	 * @throws IOException if something goes wrong while parsing.
	 */
	public static Employee fromJSON(final InputStream in) throws IOException {

		// the fields read from JSON
		UUID jEmployeeId = null;
		String jSurname = null;
		String jName = null;
		int jNOperation = -1;
		float jSalary = -1;
		String jRole = null;

		final JsonParser jp = JSON_FACTORY.createParser(in);

		// while we are not on the start of an element or the element is not
		// a token element, advance to the next element (if any)
		while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !"employee".equals(jp.getCurrentName())) {

			// there are no more events
			if (jp.nextToken() == null) {
				throw new IOException("Unable to parse JSON: no employee object found.");
			}
		}

		while (jp.nextToken() != JsonToken.END_OBJECT) {

			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

				switch (jp.getCurrentName()) {
					case "employeeId":
						jp.nextToken();
						jEmployeeId = UUID.fromString(jp.getText());
						break;
					case "surname":
						jp.nextToken();
						jSurname = jp.getText();
						break;
					case "name":
						jp.nextToken();
						jName = jp.getText();
						break;
					case "nOperation":
						jp.nextToken();
						jNOperation = jp.getIntValue();
						break;
					case "salary":
						jp.nextToken();
						jSalary = jp.getFloatValue();
						break;
					case "role":
						jp.nextToken();
						jRole = jp.getText();
						break;
				}
			}
		}

		return new Employee(jEmployeeId, jSurname, jName, jNOperation, jSalary, jRole);
	}
}