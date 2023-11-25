package gr.evansp.todofullstackappbackend.user.repository;

import gr.evansp.todofullstackappbackend.user.models.User;
import gr.evansp.todofullstackappbackend.utils.NumberUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Integration test for {@link UserRepository}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {

  private final List<User> users = new ArrayList<>();
  
  @Autowired
  UserRepository repository;

  @After
  public void cleanup() {
    for (User user : users) {
      repository.delete(user);
    }
  }

  @Test
  public void testSave() {
    User user = repository.save(createSampleUser());
    assertNotNull(user);
    assertTrue(user.getUserId() > 0);
    users.add(user);
  }

  @Test
  public void testDelete() {
    User user;
    if (users.isEmpty()) {
      user = repository.save(createSampleUser());
      users.add(user);
    } else {
      user = users.get(users.size() - 1);
    }

    repository.delete(user);
    User removed = users.remove(users.size() - 1);

    assertNull(repository.findById(removed.getUserId()).orElse(null));
  }


  private User createSampleUser() {
    User user = new User();

    user.setEmail(String.format("example%d@example.com", NumberUtils.generateRandomLong(0L, 1000000L)));
    user.setPassword("12345678");

    return user;
  }
}