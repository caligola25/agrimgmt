package it.unipd.dei.webapp.database.warehouse_worker;

import it.unipd.dei.webapp.resource.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
	* Show the catalogue of product that the factory produce.
	
	* @author ---
	* @version 1.00
	* @since 1.00
	*/
public final class CatalogueDatabase{

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM Factory.product WHERE available=true ";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * Creates a new object for showing the catalogue of the factory's products.
	 *
	 * @param con
	 *            the connection to the database.
	 */
	public CatalogueDatabase (final Connection con){
		this.con = con;
	}


	/**
	 * Stores the list of all product to show in the catalogue
	 *
	 * @return a List of Product representing the whole catalogue
	 * @throws SQLException
	 *             if any error occurs while retrieving information.
	 */
	public List<Product> showCatalogue() throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		final List<Product> products = new ArrayList<Product>();
		
		try{
			pstmt = con.prepareStatement(STATEMENT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
					
					products.add(new Product((UUID) rs.getObject("product_id"),
							rs.getString("product_name"),
							rs.getFloat("price"),
							rs.getBoolean("available")));
			}
		}finally{
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			con.close();
		}

		return products;
		
	}
	
}