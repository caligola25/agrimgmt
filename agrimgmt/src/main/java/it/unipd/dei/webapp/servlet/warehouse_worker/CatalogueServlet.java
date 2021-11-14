package it.unipd.dei.webapp.servlet.warehouse_worker;

import it.unipd.dei.webapp.database.warehouse_worker.CatalogueDatabase;
import it.unipd.dei.webapp.resource.Product;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Show the catalogue of product.

 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class CatalogueServlet extends AbstractDatabaseServlet {

	/**
	 * Show the catalogue of product.
	 *
	 * @param req
	 *            the HTTP request from the client.
	 * @param res
	 *            the HTTP response from the server.
	 *
	 * @throws ServletException
	 *             if any error occurs while executing the it.unipd.dei.webapp.servlet.
	 * @throws IOException
	 *             if any error occurs in the client/server communication.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		List<Product> elements = null;
		Message m = null;
		
		try {
			elements = new CatalogueDatabase(getDataSourceR().getConnection())
						.showCatalogue();
			m = new Message("Catalogue correctly found.");

			req.setAttribute("message", m);
			req.setAttribute("elements",elements);

		}catch (SQLException ex) {
				m = new Message("Cannot show catalogue: unexpected error while accessing the database.", 
						"E200", ex.getMessage());
				req.setAttribute("message", m);
		}

		req.getRequestDispatcher("/jsp/show-catalogue.jsp").forward(req, res);
	}
}