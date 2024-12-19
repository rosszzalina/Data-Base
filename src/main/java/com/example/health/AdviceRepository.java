package com.example.health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdviceRepository {
    private final Connection connection;

    public AdviceRepository(Connection connection) {
        this.connection = connection;
    }

    public List<String> getAdviceForCondition(String condition) throws SQLException {
        String query = "SELECT advice FROM diseaseadvice WHERE condition = ?";
        List<String> adviceList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, condition);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    adviceList.add(resultSet.getString("advice"));
                }
            }
        }

        return adviceList;
    }
}
