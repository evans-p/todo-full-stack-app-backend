package gr.evansp.todofullstackappbackend.user.repository;

import gr.evansp.todofullstackappbackend.user.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Integration test for {@link UserRepository}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {

  @Autowired
  UserRepository repository;


  @Test
  public void test() {
    List<User> list = repository.findAll();
    assertEquals(0, list.size());
  }
}