package ormFramework.core;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EntityManager {

    <T> T findById(int id, Class<T> type) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    <T> boolean persist(T entity) throws IllegalAccessException, SQLException;

    <T> String getValuesToString(Field field,T entity) throws IllegalAccessException;

    <T> boolean delete(T entity) throws IllegalAccessException, SQLException;

    <T> boolean alterTable(T entity) throws SQLException;

}
