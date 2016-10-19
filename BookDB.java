     package book.maintenance;

import java.sql.*;

/**
 * Programmer: Jhed Factolerin<br>
 * Program: BookDB.java <br>
 * Date: October 2016 <br>
 * version 1.0
 */
public class BookDB {

    /**String for username**/
    private String user;
    /**String for password**/
    private String password;
    /**The driver name**/
    private String driver;
    /**ResultSet object**/
    private ResultSet rs;
    /**Statement object*/
    private Statement st;
    /**Connection Object**/
    private Connection con;

    /**
     * Method to connect to the database
     */
    public void connect() {
        try {
            user = "";
            password = "";
            driver = "net.ucanaccess.jdbc.UcanaccessDriver";
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:ucanaccess://" + "D://Users//Books.mdb", user, password);
            con.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC/ODBC Driver");
            System.out.println("Class Error: " + e);
        } catch (SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
        }
    }

    /**
     * Method to open the connection
     * @param sSQL
     * @throws SQLException
     */
    public void open(String sSQL) throws SQLException {
        st = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery(sSQL);
        rs.next();
    }

    /**
     * Method to close the ResultSet Statement and Connection
     * @throws SQLException
     */
    public void close() throws SQLException {
        rs.close();
        st.close();
        con.close();
    }

    /**
     * Method to get the ResultSet
     * @return ResultSet
     */
    public ResultSet getResultSet() {
        return rs;
    }

    /**
     * Method that verifies if the BookCode already exists when updating record
     * @param one 
     * @throws Exception
     * @return valid
     */
    public boolean checkPrimaryKeys(String one) throws Exception {
        boolean valid = false;
        rs = st.executeQuery("SELECT BookCode, BookTitle, BookPrice FROM Books");
        while (rs.next()) {
            if (one.equals(rs.getString("BookCode"))) {
                valid = false;
                throw new Exception("BookCode already exists!");
            } else {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * Method that moves the cursor to the first record
     * @param one
     * @return one
     */
    public Book moveFirst(Book one) {
        try {
            if (!rs.isFirst()) {
                rs.first();
                one.setCode(rs.getString(1));
                one.setTitle(rs.getString(2));
                one.setPrice(rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error on move first: " + e);
        }
        return one;
    }

    /**
     * Method that moves the cursor to the next record
     * @param one
     * @return one
     */
    public Book moveNext(Book one) {
        try {
            if (!rs.isLast()) {
                rs.next();
                one.setCode(rs.getString(1));
                one.setTitle(rs.getString(2));
                one.setPrice(rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error on move next: " + e);
        }
        return one;
    }

    /**
     * Method that moves the cursor to the previous record
     * @param one
     * @return one
     */
    public Book movePrevious(Book one) {
        try {
            if (!rs.isFirst()) {
                rs.previous();
                one.setCode(rs.getString(1));
                one.setTitle(rs.getString(2));
                one.setPrice(rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error on move previous: " + e);
        }
        return one;
    }

    /**
     * Method that moves the cursor to the last record
     * @param one
     * @return one
     */
    public Book moveLast(Book one) {
        try {
            if (!rs.isLast()) {
                rs.last();
                one.setCode(rs.getString(1));
                one.setTitle(rs.getString(2));
                one.setPrice(rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error on move last: " + e);
        }
        return one;
    }

    /**
     * Method that moves the cursor to the specified index
     * @param one
     * @param index
     * @return one
     */
    public Book moveToIndex(Book one, int index) {
        try {
            rs.absolute(index);
            one.setCode(rs.getString(1));
            one.setTitle(rs.getString(2));
            one.setPrice(rs.getDouble(3));

        } catch (SQLException e) {
            System.out.println("SQL Error on move to index: " + e);
        }
        return one;
    }

    /**
     * Method that adds record
     * @param one
     */
    public void addRecord(Book one) {
        try {
            rs.moveToInsertRow();
            rs.updateString("BookCode", one.getCode());
            rs.updateString("BookTitle", one.getTitle());
            rs.updateDouble("BookPrice", one.getPrice());
            rs.insertRow();
            rs.moveToCurrentRow();
            rs = st.executeQuery("SELECT BookCode, BookTitle, BookPrice FROM Books");
            rs.next();
        } catch (SQLException e) {
            System.out.println("SQL Error on addRecord: " + e);
        }
    }

    /**
     * Method that updates record
     * @param one
     * @throws Exception
     */
    public void updateRecord(Book one) throws Exception {
        try {
            rs.moveToCurrentRow();
            rs.updateString("BookCode", one.getCode());
            rs.updateString("BookTitle", one.getTitle());
            rs.updateDouble("BookPrice", one.getPrice());
            rs.updateRow();
        } catch (SQLException e) {
            throw new Exception("BookCode already exists!");
        }

    }

    /**
     * Method that deletes record
     */
    public void deleteRecord() {
        try {
            rs.deleteRow();
        } catch (SQLException e) {
            System.out.println("SQL Error on deleteRecord: " + e);
        }
    }
    
    /**
     * Method that counts the total number of rows
     * @param i
     * @return i
     */
    public int numberOfRows(int i){
          try {
            rs = st.executeQuery("SELECT BookCode, BookTitle, BookPrice FROM Books");
            rs.last();
            i = rs.getRow();
            rs.first();
        } catch (SQLException e) {
            System.out.println("SQL Error on deleteRecord: " + e);
        }
          return i;
    }

    /**
     * Method that commits the executeUpdate
     */
    public void commit() {
        try {
            con.commit();
            rs = st.executeQuery("SELECT BookCode, BookTitle, BookPrice FROM Books");
            rs.next();
        } catch (SQLException e) {
            System.out.println("SQL Error on commit: " + e);
        }
    }

    /**
     * Method that dismiss the executeUpdate
     */
    public void rollback() {
        try {
            con.rollback();
            rs = st.executeQuery("SELECT BookCode, BookTitle, BookPrice FROM Books");
            rs.next();
        } catch (SQLException e) {
            System.out.println("SQL Error on commit: " + e);
        }
    }
}
