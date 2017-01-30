package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alebazi on 2017-01-30.
 */
public class DAOCommonUtils {

    private final static String ONE_TEN = "1 - 10";
    private final static String ONE_TWENTY = "1 - 20";
    private final static String ONE_FIFTY = "1 - 50";
    private final static String ONE_HUNDRED = "1 - 100";

    public static boolean checkIfInRange(ResultSet next, String range) throws SQLException {

        switch (range) {
            case ONE_TEN:
                if (!next.getBoolean("IF_ONE_TEN")) {
                    return false;
                }
                break;
            case ONE_TWENTY:
                if (!next.getBoolean("IF_ONE_TWENTY")) {
                    return false;
                }
                break;
            case ONE_FIFTY:
                if (!next.getBoolean("IF_ONE_FIFTY")) {
                    return false;
                }
                break;
            case ONE_HUNDRED:
                if (!next.getBoolean("IF_ONE_HUNDRED")) {
                    return false;
                }
                break;
        }
        return true;
    }
}
