package model;

import java.sql.SQLException;

/**
 * Created by John on 1/18/2017.
 */
public class DataReader {

    private AppConnection conn = new AppConnection();

    public DataReader() {
        getData();
    }

    int rowIndex;
    public String[][] getData() {
        rowIndex = 0;
        conn.loadDriver();
        conn.connectToDB();

        int rowCount = 0;

        conn.doSomething("select count(surname) from mytable");
        try {
            conn.query();
            while (conn.getRS().next()) {
                rowCount = conn.getRS().getInt(1);
            }
            System.out.println(rowCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[][] rows = new String[rowCount][9];
        conn.doSomething("select * from mytable order by group, surname");
        try {
            conn.query();
            while (conn.getRS().next()) {
                for(int i = 0; i < rows[rowIndex].length - 2; i++) {
                    rows[rowIndex][i] = conn.getRS().getString(i + 1);
                }
                rows[rowIndex][7] = "UPDATE";
                rows[rowIndex][8] = "DELETE";

                rowIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static void main(String[] args) {
        new DataReader();
    }
}
