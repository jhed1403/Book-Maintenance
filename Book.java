package book.maintenance;

import java.text.DecimalFormat;

/**
 * Programmer: Jhed Factolerin<br>
 * Program: Book.java <br>
 * Date: October 2016 <br>
 * version 1.0
 */
public class Book {

    /**BookCode**/
    private String code;
    /**BookTitle**/
    private String title;
    /**BookPrice**/
    private double price;

    /**
     * Constructor for the class Book
     * @param code
     * @param title
     * @param price
     */
    public Book(String code, String title, double price) {
        this.code = code;
        this.title = title;
        this.price = price;
    }

    /**
     * Method that gets the code
     * @return code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Method that gets the title
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Method that gets the price
     * @return price
     */
    public double getPrice() {
        return this.price;
    }
    
    /**
     * Method that sets the code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Method that sets the title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method that sets the price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method that formats the price to currency format with $ sign
     * @param price
     * @return currency
     */
    public String currencyFormat(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        String formatted = df.format(price);
        String currency = "$" + formatted;
        return currency;
    }

    /**
     * Method that removes the $ sign from the price
     * @param price
     * @return p
     */
    public String removeDollar(String price) {
        String result = price.replaceAll("\\.0*$", "");
        String p = result.replaceAll("[$]", "");
        return p;
    }
}
