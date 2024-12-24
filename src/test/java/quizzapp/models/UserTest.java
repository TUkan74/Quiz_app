package quizzapp.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the User class.
 */
public class UserTest {

    @Test
    public void testUserInitialization() {
        User user = new User("JohnDoe");

        assertEquals("JohnDoe", user.get_username());
        assertEquals(0, user.get_actualPoints());
        assertEquals(0, user.get_maxPoints());
        assertEquals(0, user.get_totalAttempts());
    }

    @Test
    public void testUpdateScore() {
        User user = new User("JohnDoe");

        user.update_score(8, 10);
        assertEquals(8, user.get_actualPoints());
        assertEquals(10, user.get_maxPoints());
        assertEquals(1, user.get_totalAttempts());

        user.update_score(7, 15);
        assertEquals(15, user.get_actualPoints());
        assertEquals(25, user.get_maxPoints());
        assertEquals(2, user.get_totalAttempts());
    }

    @Test
    public void testTotalScore() {
        User user = new User("JohnDoe");

        user.update_score(5, 10);
        user.update_score(10, 20);

        assertEquals("15 / 30", user.get_totalScore());
    }

    @Test
    public void testSuccessPercentage() {
        User user = new User("JohnDoe");

        assertEquals(0.0, user.get_successPercentage(), 0.01);

        user.update_score(8, 10);
        assertEquals(80.0, user.get_successPercentage(), 0.01);

        user.update_score(7, 15);
        assertEquals(60.0, user.get_successPercentage(), 0.01);
    }

    @Test
    public void testInvalidScoreUpdate() {
        User user = new User("JohnDoe");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.update_score(-5, 10);
        });
        assertEquals("Scored points and maximum points must not be negative.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            user.update_score(5, -10);
        });
        assertEquals("Scored points and maximum points must not be negative.", exception.getMessage());
    }

    @Test
    public void testEquality() {
        User user1 = new User("JohnDoe");
        User user2 = new User("JohnDoe");
        User user3 = new User("JaneDoe");

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
    }

    @Test
    public void testHashCode() {
        User user1 = new User("JohnDoe");
        User user2 = new User("JohnDoe");

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testToString() {
        User user = new User("JohnDoe");

        user.update_score(8, 10);
        user.update_score(12, 20);

        String expected = "User{username='JohnDoe', totalScore='20 / 30', successPercentage=66.67%, totalAttempts=2}";
        assertEquals(expected, user.toString());
    }
}
