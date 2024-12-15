package ormFramework.core;

import bg.codexio.customOrmDemo.entity.Employee;
import ormFramework.annotation.Column;
import ormFramework.annotation.Entity;
import ormFramework.annotation.Id;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityManagerImpl implements EntityManager {

    private final Connection connection;

    public EntityManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T> T findById(int id, Class<T> type) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String tableName = type.getAnnotation(Entity.class).tableName();
        String idColumnName = Arrays.stream(type.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow()
                .getName();
        //Итерираме по асболютно всичките fieldove,който взимаме чрез getDeclaredFields, с помощта на Reflection.Взимаме всички fieldove на даденото Entity
        //правим филтрация(понеже търсим само ид,търсим само тези,които имат анотацията ид) -> f.isAnnotationPresent(Id.class)
        //Взимаме първия -> Като това ни връща Optional и имаме възможност в противен случай да хвърлим грешка,което е добра практика(можем да подадем supplier)
        //И да вземем неговото име -> .getName();

        PreparedStatement stmt
                = this.connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ?");

        stmt.setInt(1, id);

        T entity = (T) type.getConstructors()[0].newInstance();
        //Намираме ентитито като знаейки типът му можем да го кастнем безопасно към Т


        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnInfo = field.getAnnotation(Column.class);
                String setterName = "set" + ((field.getName().charAt(0) + "").toUpperCase()) + field.getName().substring(1);
                if (field.getType().equals(String.class)) {
                    String s = rs.getString(columnInfo.name());
                    type.getMethod(setterName, String.class).invoke(entity, s);
                } else {
                    int s = rs.getInt(columnInfo.name());
                    type.getMethod(setterName, field.getType()).invoke(entity, s);
                }
            } else if (field.isAnnotationPresent(Id.class)) {
                String setterName = "set" + ((field.getName().charAt(0) + "").toUpperCase()) + field.getName().substring(1);
                type.getMethod(setterName, int.class).invoke(entity, id);
            }
        }


        return entity;
    }

    @Override
    public <T> boolean persist(T entity) throws IllegalAccessException, SQLException {
        //Как трябва да разбере нашият ОРМ дали да прави инсърт или delete?
        //Трябва да видим дали имаме ид
        //Как се случва това?Трябва да вземем нашето ид
        //Понеже ще ни се налага често да търсим ид,ще си го изнесем в нов метод
        Field idField=getIdFieldFromEntity(entity);//Ще връща единствено fielda, който е анотиирам с анотацията id
        //За да вземем от нашият филд стойността му ние първо трябва да го сетнем на setAccesible(true)
        idField.setAccessible(true);// Защо го правим това? За да можем вече да вземем стойността
        //Field idField=getIdFieldFromEntity(entity) --> Тук работим още със самия филд от рефлекшъна,
        //а сега вече искаме да вземем стойността на това поле и тъй като знаем,че то е инт можем спокойно да го кастнем
        //ТЪЙ КАТО ВЗИМАЙКИ С РЕФЛЕКШЪН ВИНАГИ ДОСТЪПВАМЕ ОБЕКТИ
        int id= (int) idField.get(entity);//idField.get -> По този начин ние подаваме на филда обекта(малко обратна логика).Обекта го имаме(entity),
        //Искам от обекта да ми вземеш стойноста
        if(id == 0){
             return doInsert(entity);
        }
        return doUpdate(id,entity);

    }

    private <T> boolean doUpdate(int id, T entity) throws SQLException {
        String tableName = getTableNameByEntity(entity);

//        Arrays.stream(entity.getClass().getDeclaredFields())
//                .filter(field -> field.isAnnotationPresent(Column.class))
//                .map(field -> {
//                    try {
//                        return getValuesToString(field, entity);
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                })
////                    String fieldName=field.getAnnotation(Column.class).name();
//                .collect(Collectors.joining(", "));


        String fieldNameAndValues=getFiledAndValuesAsString(entity);

        String updateQuery = String.format("UPDATE %s " + "SET %s " +"WHERE id = ?;",tableName,fieldNameAndValues );

        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setInt(1,id);

        return preparedStatement.executeUpdate() > 0;
    }

    private <T> String getFiledAndValuesAsString(T entity) {
           StringBuilder stringBuilder=new StringBuilder();
        Arrays.stream(entity.getClass().getDeclaredFields())
        .filter(field -> field.isAnnotationPresent(Column.class))
            .forEach(field -> {
            field.setAccessible(true);
            String name = field.getAnnotation(Column.class).name();
            String type = field.getAnnotation(Column.class).columnDefinition();
            Object fieldValue=null;
            try {
                fieldValue = field.get(entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if(type.equals("DATE") || type.startsWith("VARCHAR")){
               stringBuilder.append(name).append(" = ").append(" '").append(fieldValue).append("'");
            }else{
                stringBuilder.append(name).append(" = ")
                .append(" ").append(fieldValue).append(" ");
            }
        });
        return stringBuilder.toString();
    }

    @Override
    public <T> String getValuesToString(Field field, T entity) throws IllegalAccessException {
        String type = field.getAnnotation(Column.class).columnDefinition();
        field.setAccessible(true);
        if(type.equals("DATE") || type.startsWith("VARCHAR")){
            try{
                return String.format(" '%s' ",field.get(entity));
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return String.format(" %s ",field.get(entity));
    }

    @Override
    public <T> boolean delete(T entity) throws IllegalAccessException, SQLException {
        Field fieldId = getIdFieldFromEntity(entity);
        fieldId.setAccessible(true);//За да не ударим грешката, че нямаме достъп
        int id = (int) fieldId.get(entity); //чрез рефлекшън имаме достъп до метода;Всеки един обект, който взимам чрез Рефлекшън идва като Object;Kastvame го защото сме сигурни,че е от тип int
        String tableName = getTableNameByEntity(entity);
  // КОГАТО ВЗЕМЕМ OBJECTA НИЕ ВЕЧЕ ИМАМЕ ДОСТЪП ДО КЛАС КОЙТО ИСКАМЕ
        String deleteQuery =String.format( "DELETE FROM %s WHERE id = ?",tableName);

        PreparedStatement preparedStatement= connection.prepareStatement(deleteQuery);
        preparedStatement.setInt(1,id);

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public <T> boolean alterTable(T entity) throws SQLException {
        Set<String> columnsInTable = getAllColumnsInTableBy(entity);
        return true;
    }

    private <T> Set<String> getAllColumnsInTableBy(T entity) throws SQLException {
        Set<String>allColumns=new HashSet<>();

        String query = "";
        PreparedStatement preparedStatement= connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
        //  allColumns.add

        }
        return allColumns;
    }


    private <T> boolean doInsert(T entity) throws SQLException {
        //Първо трябва да намерим името на таблицата
        String tableName=getTableNameByEntity(entity);

        String fieldsName = getFieldNameBy(entity.getClass());

        String fieldValues = getFieldValuesAsString(entity);

        System.out.println();
        //Какво ни трябва да инсърт? sql
        String query=String.format("INSERT INTO %s (%s) VALUES (%s) ",tableName,fieldsName);

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        return preparedStatement.executeUpdate() > 0;
    }

    private <T> String getFieldValuesAsString(T entity) {
        StringBuilder sb=new StringBuilder();

                 Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    String name = field.getAnnotation(Column.class).name();
                    String type = field.getAnnotation(Column.class).columnDefinition();
                    Object fieldValue=null;
                    try {
                        fieldValue = field.get(entity);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    if(type.equals("DATE") || type.startsWith("VARCHAR")){
                        sb.append(" '").append(fieldValue).append("'");
                    }else{
                        sb.append(" ").append(fieldValue).append(" ");
                    }
                });

//        return Arrays.stream(entity.getClass().getDeclaredFields())
//                .filter(field -> field.isAnnotationPresent(Column.class))
////                .map(field -> {
////                    return getValueToString(field,entity);
////                })
//                .collect(Collectors.joining(", "));
        return sb.toString();
    }

    private String getFieldNameBy(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> {
                    return field.getAnnotation(Column.class).name();
                })
                .collect(Collectors.joining(", "));


    }

    private <T> String getTableNameByEntity(T entity) {
        //В нашия User клас сложихме анотацията @tableName
        return entity.getClass().getAnnotation(Entity.class).tableName();
    }

    private <T> Field getIdFieldFromEntity(T entity) {
        //Работейки с enityto ние можем да вземем неговия клас.
        //Искаме да вземем всичките му fieldove -- На мен ми трябва единствено филда с анотация Ид
        return   Arrays.stream(entity.getClass().getDeclaredFields())
                .filter( field -> field.isAnnotationPresent(Id.class))
                .findFirst().orElseThrow((() -> new UnsupportedOperationException("Entity doesn't have id")));
        //isAnnotationPresent ми дава възможност да взема само филд с анотация ид
    }

//    private <T> String getValueToString(Field field, T entity) {
//        return null;
//    }
}
