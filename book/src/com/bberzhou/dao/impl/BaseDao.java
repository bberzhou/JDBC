package com.bberzhou.dao.impl;

import com.bberzhou.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/4/2021
 * Create By Intellij IDEA
 */
public abstract class BaseDao {
    /**
     *  使用dbutils 工具类来操作数据库
     */
    private QueryRunner queryRunner = new QueryRunner();



    /**
     * update() 方法用来执行： insert/ update/ Delete相关的操作
     * @param sql   要执行的sql 语句
     * @param args  可变参数，用来预编译sql语句的时候填充占位符
     * @return  如果返回值为-1 表示执行失败；
     *          如果返回其他值，表示受影响的行数
     */
    public int update(String sql, Object... args) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        try {
            int update = queryRunner.update(conn, sql, args);
            //  正常情况下直接返回受影响的行数
            return  update;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //  关闭连接
            JdbcUtils.close(conn);
        }
        return -1;
    }

    /**
     *  查询返回一个JavaBean的sql语句，注意这是一个泛型方法！
     * @param t 返回的对象类型
     * @param sql 要执行的sql语句
     * @param args  sql语句对应的参数
     * @param <T>   返回的类型的泛型
     * @return  返回查询结果
     * @throws SQLException
     */
    public <T> T queryForOne(Class<T> t, String sql, Object... args ) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        try {
            //  如果查询到结果，就直接返回
            //  使用BeanHandler 用于封装数据库表中的一条记录
            BeanHandler<T> tBeanHandler = new BeanHandler<T>(t);
            T query = queryRunner.query(conn, sql, tBeanHandler, args);
            return query;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     *  查询返回多个JavaBean的sql语句
     * @param t 返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql语句对应的参数
     * @param <T>   返回的类型的泛型
     * @return      返回一个list集合
     * @throws SQLException
     */
    public <T> List<T> queryForList(Class<T> t, String sql, Object... args) throws SQLException {
        //  获取数据库连接
        Connection conn = JdbcUtils.getConnection();
        //  创建一个tBeanListHandler 对象
        BeanListHandler<T> tBeanListHandler = new BeanListHandler<>(t);
        try {
            List<T> tList = queryRunner.query(conn,sql,tBeanListHandler,args);
            return tList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     *  执行返回一行一列的sql语句
     * @param sql   要执行的sql语句
     * @param args  传入sql对应的参数
     * @return  返回查询到的值
     * @throws SQLException
     */
    public Object queryForSingleValue(String sql,Object... args) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        ScalarHandler<Object> objectScalarHandler = new ScalarHandler<>();
        try {
            Object query = queryRunner.query(conn, sql, objectScalarHandler, args);
            //  返回查询到的单列值
            return query;
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            //  关闭连接
            JdbcUtils.close(conn);
        }
        return null;

    }
}

