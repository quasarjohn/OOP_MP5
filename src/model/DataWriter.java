package model;

import java.sql.SQLException;

/**
 * Created by John on 1/18/2017.
 */
public class DataWriter {

    private AppConnection conn;

    public boolean addRow(TableRow row) {
        conn = new AppConnection();
        conn.loadDriver();
        conn.connectToDB();

        conn.doSomething("insert into mytable values(" +
                row.getGroup() + ",'" +
                row.getFirstName() + "','" +
                row.getSurname() + "','" +
                row.getAddress() + "','" +
                row.getBaptism() + "','" +
                row.getBirthday() + "','" +
                row.getKapisanan() + "')"
        );

        try {
            conn.update();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRow(String firstName, String lastName, TableRow row) {
        conn = new AppConnection();
        conn.loadDriver();
        conn.connectToDB();

        conn.doSomething("update mytable " +
                "set group = " + row.getGroup() + ", " +
                "surname = '" + row.getSurname() + "', " +
                "first_name = '" + row.getFirstName() + "'," +
                "address = '" + row.getAddress() + "'," +
                "baptism = '" + row.getBaptism() + "'," +
                "birthday ='" + row.getBirthday() + "'," +
                "kapisanan = '" + row.getKapisanan() + "' where " +
                "surname = '" + lastName + "' AND " +
                "first_name = '" + firstName + "'");

        try {
            conn.update();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRow(String firstName, String lastName) {
        conn = new AppConnection();
        conn.loadDriver();
        conn.connectToDB();

        conn.doSomething("delete from mytable where first_name = '" + firstName + "' " +
                "AND surname ='" + lastName + "'");
        try {
            conn.update();
            conn.closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
