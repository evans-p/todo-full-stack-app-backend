package gr.evansp.todofullstackappbackend.repositories.users;

import gr.evansp.todofullstackappbackend.models.users.User;
import gr.evansp.todofullstackappbackend.samples.Samples;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
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
class TestUserRepository {


  private final List<User> users = new ArrayList<>();
  @Autowired
  UserRepository repository;

  @AfterEach
  public void cleanup() {
    for (User user : users) {
      repository.delete(user);
    }
  }

  @Test
  void testSave() {
    User user = repository.save(Samples.createSampleUser());
    assertNotNull(user);
    assertTrue(user.getUserId() > 0);
    users.add(user);
  }

  @Test
  void testDelete() {
    User user;
    if (users.isEmpty()) {
      user = repository.save(Samples.createSampleUser());
      users.add(user);
    } else {
      user = users.get(users.size() - 1);
    }

    repository.delete(user);
    User removed = users.remove(users.size() - 1);

    assertNull(repository.findById(removed.getUserId()).orElse(null));
  }
}