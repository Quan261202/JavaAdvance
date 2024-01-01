package com.halloween.dao.impl;

import com.halloween.dao.GenericDAO;
import com.halloween.mapper.INewMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {

    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/java_advance", "root", "123456");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<T> query(String sql, INewMapper<T> mapper, Object... params) {
        List<T> results = new ArrayList<>();
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
        List<G> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            con = getCon();
            if(con != null)
            {
                stm = con.prepareStatement(sql);
                // set parameter
                setParameter(stm, params);
                resultSet = stm.executeQuery();
                while (resultSet.next())
                    results.add(getParameter(resultSet, 1, gClass));
                return results;
            }
            return null;
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
            int id = 0;
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            if (resultSet.next()) id = resultSet.getInt(1);
            con.commit();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
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
        	e.printStackTrace();
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
            setParameter(stm, params);
            resultSet = stm.executeQuery();
            if (resultSet.next()) return getParameter(resultSet, columnIndex, gClass);
        } catch (SQLException e) {
            return null;
        }
        finally {
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
            else if (param instanceof Boolean) stm.setBoolean(i + 1, (Boolean) param);
            else if (param instanceof Float) stm.setFloat(i + 1, (Float) param);
            else if (param instanceof String) stm.setString(i + 1, (String) param);
            else if (param instanceof Double) stm.setDouble(i + 1, (Double) param);
            else if (param instanceof java.util.Date date) stm.setTimestamp(i + 1, new Timestamp(date.getTime()));
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
