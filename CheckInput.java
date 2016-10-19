package book.maintenance;

/**
 * Programmer: Jhed Factolerin<br>
 * Program: CheckInput.java <br>
 * Date: October 2016 <br>
 * version 1.0
 */
public class CheckInput {

    /**
     * Method that checks if BookCode entered by user is valid
     * @param code
     * @return valid
     * @throws java.lang.Exception
     */
    public static boolean checkCode(String code) throws Exception {
        boolean valid = false;
        if (code.equals("")) {
            throw new Exception("Please enter BookCode!");
        } else if (code.length() > 4) {
            throw new Exception("BookCode can only contain four characters!");
        } else {
            valid = true;
        }
        return valid;
    }

    /**
     * Method that checks if BookTitle entered by user is valid
     * @param title
     * @return valid
     * @throws java.lang.Exception
     */
    public static boolean checkTitle(String title) throws Exception {
        boolean valid = false;
        if (title.equals("")) {
            throw new Exception("Please enter BookTitle!");
        } else {
            valid = true;
        }
        return valid;
    }

    /**
     * Method that checks if BookPrice entered by user is valid
     * @param price
     * @return valid
     * @throws java.lang.Exception
     */
    public static boolean checkPrice(String price) throws Exception {
        boolean valid = false;
        if (price.equals("")) {
            throw new Exception("Please enter BookPrice!");
        } else if (price.matches("[0-9]{1,13}(\\.[0-9]{2})?")) {
            double p = Double.parseDouble(price);
            if (p < 0) {
                throw new Exception("Invalid Price!");
            } else {
                valid = true;
            }
        } else {
            throw new Exception("Price must be a double!");
        }
        return valid;
    }

}
