package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {

        List<String> overPopulatedDinosaurs = new ArrayList<>();
        String SQL = " select breed from dinosaur where actual > expected order by breed";

        try {

            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                overPopulatedDinosaurs.add(resultSet.getNString(1));
            }

        } catch (SQLException throwable) {

            throwable.printStackTrace();

        }

        return overPopulatedDinosaurs;

    }

}
