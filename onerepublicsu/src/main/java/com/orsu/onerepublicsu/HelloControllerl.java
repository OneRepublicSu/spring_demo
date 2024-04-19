package com.orsu.onerepublicsu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

@RestController
public class HelloControllerl {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
        return "Hello OR！";
    }

    @RequestMapping(value = "/databases", method = RequestMethod.GET)
    public String MySQLReader() {
        String url = "jdbc:mysql://localhost:3306/my_new_database";
        String user = "root";
        String password = "123456";
        String res = "";

        try {
            // 加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立连接
            Connection conn = DriverManager.getConnection(url, user, password);

            // 创建Statement
            Statement stmt = conn.createStatement();

            // 执行查询
            ResultSet rs = stmt.executeQuery("SELECT * FROM demo");


            // 遍历结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                res = res + "[id:" + id + "name:" + name + "]";
            }

            // 关闭资源
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}

