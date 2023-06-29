package com.haemimont.cars.core.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SqlBuilder {
    final Map<Integer, String> map = new HashMap<>();
    private final Connection connection;
    boolean whereClause = false;
    PreparedStatement preparedStatement;
    private String sql;
    private int parameterCount = 1;

    //    Map<Integer,String> mapForPreparedStatement = new HashMap<>();
    public SqlBuilder(String sql, Connection connection) {
        this.sql = sql;
        this.connection = connection;
    }

    public String whereOrAnd(String sql) {
        if (whereClause) {
            sql += " AND ";
        } else {
            sql += " where ";
            whereClause = true;
        }
        return sql;
    }

    public void equation(String dbFieldName, String value) {
        if (value != null && !value.equals("")) {
            sql = whereOrAnd(sql);
            sql += dbFieldName + "=?";
//            preparedStatement.setString(parameterCount,value);
            map.put(parameterCount, value);
            parameterCount++;
        }
    }

    public void biggerThan(String dbFieldName, String value) {
        if (value != null && !value.equals("")) {
            sql = whereOrAnd(sql);
            sql += dbFieldName + ">?";
//            preparedStatement.setString(parameterCount,value);
            map.put(parameterCount, value);
            parameterCount++;
        }
    }

    public void smallerThan(String dbFieldName, String value) {
        if (value != null && !value.equals("")) {
            sql = whereOrAnd(sql);
            sql += dbFieldName + "<?";
//            preparedStatement.setString(parameterCount,value);
            map.put(parameterCount, value);
            parameterCount++;
        }
    }

    public ResultSet execute() throws SQLException {
        this.preparedStatement = connection.prepareStatement(sql);
        for (Integer key : map.keySet()) {
            preparedStatement.setString(key, map.get(key));
        }
        return preparedStatement.executeQuery();
    }


}
