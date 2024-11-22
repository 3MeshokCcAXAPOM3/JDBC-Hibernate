package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
       UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
//       userDao.chandgeType();
        userDao.saveUser("voloda", "volody", (byte) 56);

            System.out.println("Hello World!");
    }
}
