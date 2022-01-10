package com.halloween.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.halloween.dao.GenericDAO;
import com.halloween.mapper.INewMapper;

public class AbstractDAO<T> implements GenericDAO<T> {
    protected static Boolean check = false;

    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoping", "root", "");
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    private Connection getConSQL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433; databaseName=SHOPING";
            Connection con = DriverManager.getConnection(url, "sa", "1");
            return con;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<T> query(String sql, INewMapper<T> mapper, Object... params) {
        List<T> results = new ArrayList<T>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            con = getCon();
            stm = con.prepareStatement(sql);
            // set parameter
            setParameter(stm, params);
            resultSet = stm.executeQuery();
            while (resultSet.next())
                results.add(mapper.mapRow(resultSet));
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeCon(con, stm, resultSet);
        }
    }

    @Override
    public <G> List<G> getListObject(String sql, Class<G> gClass, Object... params) {
        List<G> results = new ArrayList<G>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            if (!check) con = getCon();
            else con = getConSQL();
            stm = con.prepareStatement(sql);
            // set parameter
            setParameter(stm, params);
            resultSet = stm.executeQuery();
            while (resultSet.next())
                results.add(getParameter(resultSet, 1, gClass));
            check = false;
            return results;
        } catch (SQLException e) {
            return null;
        } finally {
            closeCon(con, stm, resultSet);
        }
    }

    @Override
    public Integer insert(String sql, Object... params) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            con = getCon();
            con.setAutoCommit(false);
            stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(stm, params);
            Integer id = 0;
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            if (resultSet.next()) id = resultSet.getInt(1);
            con.commit();
            return id;
        } catch (SQLException e) {
            if (con != null)
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    return null;
                }
            return null;
        } finally {
            closeCon(con, stm, resultSet);
        }
    }

    @Override
    public Boolean updateOrDelete(String sql, Object... params) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = getCon();
            stm = con.prepareStatement(sql);
            setParameter(stm, params);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            closeCon(con, stm, null);
        }
    }

    @Override
    public <G> G getSingleObject(String sql, Integer columnIndex, Class<G> gClass, Object... params) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            con = getCon();
            stm = con.prepareStatement(sql);
            // set parameter
            setParameter(stm, params);
            resultSet = stm.executeQuery();
            if (resultSet.next()) return getParameter(resultSet, columnIndex, gClass);
        } catch (SQLException e) {
            return null;
        } finally {
            closeCon(con, stm, resultSet);
        }
        return null;
    }

    protected void closeCon(Connection con, PreparedStatement stm, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void setParameter(PreparedStatement stm, Object... params) throws SQLException {
        for (int i = 0; i < params.length; ++i) {
            Object param = params[i];
            if (param instanceof Long) stm.setLong(i + 1, (Long) param);
            else if (param instanceof Integer) stm.setInt(i + 1, (Integer) param);
            else if (param instanceof Float) stm.setFloat(i + 1, (Float) param);
            else if (param instanceof String) stm.setString(i + 1, (String) param);
            else if (param instanceof Double) stm.setDouble(i + 1, (Double) param);
            else if (param instanceof Date) stm.setDate(i + 1, new java.sql.Date(((Date) param).getTime()));
            else if (param == null) stm.setNull(i + 1, java.sql.Types.NULL);
        }
    }

    private <G> G getParameter(ResultSet rs, Integer columnIndex, Class<G> gClass) {
        try {
            return rs.getObject(columnIndex, gClass);
        } catch (SQLException e) {
            return null;
        }
    }
}
