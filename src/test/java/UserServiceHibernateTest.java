import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernate;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class UserServiceHibernateTest {
    private final UserService userServiceHib = new UserServiceHibernate();

    private final String testName = "Ivan";
    private final String testLastName = "Ivanov";
    private final byte testAge = 5;


    @Test
    public void dropUsersTable() {
        try {
            userServiceHib.dropUsersTable();
            userServiceHib.dropUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании удаления таблицы произошло исключение\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userServiceHib.dropUsersTable();
            userServiceHib.createUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userServiceHib.dropUsersTable();
            userServiceHib.createUsersTable();
            userServiceHib.saveUser(testName, testLastName, testAge);

            User user = userServiceHib.getAllUsers().get(0);

            if (!testName.equals(user.getName())
                    || !testLastName.equals(user.getLastName())
                    || testAge != user.getAge()
            ) {
                Assert.fail("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userServiceHib.dropUsersTable();
            userServiceHib.createUsersTable();
            userServiceHib.saveUser(testName, testLastName, testAge);
            userServiceHib.removeUserById(1L);
        } catch (Exception e) {
            Assert.fail("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userServiceHib.dropUsersTable();
            userServiceHib.createUsersTable();
            userServiceHib.saveUser(testName, testLastName, testAge);
            List<User> userList = userServiceHib.getAllUsers();

            if (userList.size() != 1) {
                Assert.fail("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблицы");
            }
        } catch (Exception e) {
            Assert.fail("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userServiceHib.dropUsersTable();
            userServiceHib.createUsersTable();
            userServiceHib.saveUser(testName, testLastName, testAge);
            userServiceHib.cleanUsersTable();

            if (userServiceHib.getAllUsers().size() != 0) {
                Assert.fail("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            Assert.fail("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }
    }

}
