package quizzapp.database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import quizzapp.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Database class.
 */
public class UserDatabaseTest {

    private static final Database database = new Database();
    private static final List<String> new_users = new ArrayList<>();

    @AfterAll
    public static void clearAllTestUsers() {
        System.out.println("Cleaning up test users...");
        List<User> users = database.loadUsers();
        users.removeIf(user -> new_users.contains(user.get_username()));
        database.saveUsers(users);
        System.out.println("Test resources cleared: " + new_users);
    }

    @Test
    public void testAddUser() {
        System.out.println("Running testAddUser...");
        User newUser = new User("test_user");

        database.addUser(newUser);
        new_users.add(newUser.get_username());

        List<User> users = database.loadUsers();
        assertTrue(users.stream().anyMatch(user -> user.get_username().equals("test_user")), "User not added successfully!");
        System.out.println("User added successfully: " + newUser.get_username());
    }

    @Test
    public void testDuplicateUser() {
        System.out.println("Running testDuplicateUser...");
        User newUser = new User("duplicate_user");
        database.addUser(newUser);
        new_users.add(newUser.get_username());

        // Attempt to add the same user again
        database.addUser(newUser);
        List<User> users = database.loadUsers();

        long count = users.stream().filter(user -> user.get_username().equals("duplicate_user")).count();
        assertEquals(1, count, "Duplicate user was added, but it should not be!");
        System.out.println("Duplicate user test passed for: " + newUser.get_username());
    }

    @Test
    public void testEditUser() {
        System.out.println("Running testEditUser...");
        User newUser = new User("test_user_edit");
        database.addUser(newUser);
        new_users.add(newUser.get_username());

        List<User> users = database.loadUsers();
        User userToEdit = users.stream().filter(user -> user.get_username().equals("test_user_edit")).findFirst().orElse(null);
        assertNotNull(userToEdit, "User not found for editing!");

        userToEdit.update_score(15, 30);
        database.saveUsers(users);

        List<User> updatedUsers = database.loadUsers();
        User updatedUser = updatedUsers.stream().filter(user -> user.get_username().equals("test_user_edit")).findFirst().orElse(null);

        assertNotNull(updatedUser, "User not found after editing!");
        assertEquals(15, updatedUser.get_actualPoints(), "User points not updated correctly!");
        assertEquals(30, updatedUser.get_maxPoints(), "User max points not updated correctly!");
        System.out.println("User edited successfully: " + updatedUser);
    }

    @Test
    public void testDeleteUser() {
        System.out.println("Running testDeleteUser...");
        User newUser = new User("test_user_delete");
        database.addUser(newUser);
        new_users.add(newUser.get_username());

        database.deleteUser("test_user_delete");

        List<User> users = database.loadUsers();
        assertFalse(users.stream().anyMatch(user -> user.get_username().equals("test_user_delete")), "User not deleted successfully!");
        System.out.println("User deleted successfully: " + newUser.get_username());
    }
}
