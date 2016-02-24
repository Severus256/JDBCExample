/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aga
 */
public class DBConnection {

    private String host;              // хост БД
    private String login;
    private String pass;

    private String dbName;
    private Properties prop = new Properties();    // свойста подключения
    private String url;
    private Connection mysqlConn;

    public DBConnection(String host, String login, String pass, String dbName) {
        this.host = host;
        this.login = login;
        this.pass = pass;
        this.dbName = dbName;
    }

    public void initProperties() {    // инициализируем свойства (ключ-значение/пользователь-имя пользователя и т.д.)

        url = "jdbc:mysql://" + host + "/" + dbName;                     //Правильный полный путь к БД на локальном хосте

        prop.setProperty("user", login);
        prop.setProperty("password", pass);
        prop.setProperty("characterEncoding", "UTF-8");           //кодировка читаемых данных БД
        prop.setProperty("useUnicode", "true");                  // врубаем юникод
         /*Эти параметры будут использовать в Mysql*/
        System.out.println("URL: " + url);
    }

    public void init() {                         // само подключение

        try {
            Class.forName("com.mysql.jdbc.Driver");                  // подключаем статическую часть класса. Тут - драйвер JDBC
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {                                                     //пробуем подключиться к БД
            mysqlConn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
