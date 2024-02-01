package gr.evansp.todofullstackappbackend.controllers.todos;

import gr.evansp.todofullstackappbackend.base.BaseITTest;
import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoRepository;
import gr.evansp.todofullstackappbackend.samples.Samples;
import gr.evansp.todofullstackappbackend.services.todos.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for {@link TodoController}
 */
@SuppressWarnings("nls")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TestTodoController extends BaseITTest {

  /**
   * {@link MockMvc}.
   */
  @Autowired
  MockMvc mockMvc;

  /**
   * {@link TodoService}
   */
  @Autowired
  TodoService todoService;

  /**
   * {@link TodoListRepository}
   */
  @Autowired
  TodoListRepository todoListRepository;

  /**
   * {@link TodoRepository}
   */
  @Autowired
  TodoRepository todoRepository;


  /**
   * test for {@link TodoController#readAll()}.
   *
   * @throws Exception Exception
   */
  @Test
  void testReadAll_ForbiddenGreek() throws Exception {
    SecurityContextHolder.clearContext();
    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI)
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isForbidden())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Δεν έχετε πρόσβαση.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#readAll()}.
   *
   * @throws Exception Exception.
   */
  @Test
  void testReadAll_ForbiddenEnglish() throws Exception {
    SecurityContextHolder.clearContext();
    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI))
        .andExpect(status().isForbidden())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Access denied.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#readAll()}.
   *
   * @throws Exception Exception.
   */
  @Test
  void testReadAll_Empty() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/hal+json"))
        .andExpect(content().bytes("{\"_links\":{\"self\":{\"href\":\"http://localhost/todos/\"}}}".getBytes()));
  }

  /**
   * test for {@link TodoController#readAll()}.
   *
   * @throws Exception Exception.
   */
  @Test
  void testReadAll_ok() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    todoService.store(Samples.createSampleTodo(list.getTodoListId(), SUB));

    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/hal+json"));

    todoListRepository.delete(list);
  }

  /**
   * test for {@link TodoController#read(Long)}
   *
   * @throws Exception Exception.
   */
  @Test
  void testRead_badRequestGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI + "/asd")
            .header("Accept-Language", "el-GR"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().bytes("{\"message\":\"Το αίτημα δεν μπορεί να διεκπεραιωθεί.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#read(Long)}
   *
   * @throws Exception Exception.
   */
  @Test
  void testRead_badRequestEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI + "/asd"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Request can't be processed.\",\"messages\":null}".getBytes()));
  }


  /**
   * test for {@link TodoController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_notFoundEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI + "/-1"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Todo was not found.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_notFoundGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI + "/-1")
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Η εργασία δεν βρέθηκε.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_unauthorizedGreek() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList("asd"));
    Todo todo = todoRepository.save(Samples.createSampleTodo(list.getTodoListId(), "asd"));

    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI + "/" + todo.getTodoId().toString())
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isForbidden())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Δεν έχετε πρόσβαση.\",\"messages\":null}".getBytes()));

    todoListRepository.delete(list);
    todoRepository.delete(todo);
  }

  /**
   * test for {@link TodoController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_unauthorizedEnglish() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList("asd"));
    Todo todo = todoRepository.save(Samples.createSampleTodo(list.getTodoListId(), "asd"));

    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI + "/" + todo.getTodoId().toString()))
        .andExpect(status().isForbidden())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Access denied.\",\"messages\":null}".getBytes()));

    todoListRepository.delete(list);
    todoRepository.delete(todo);
  }

  /**
   * test for {@link TodoController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_ok() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    Todo todo = todoService.store(Samples.createSampleTodo(list.getTodoListId(), SUB));

    mockMvc.perform(MockMvcRequestBuilders.get(TODOS_BASE_URI + "/" + todo.getTodoId().toString()))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/hal+json"));
    todoListRepository.delete(list);
  }

  /**
   * test for {@link TodoController#create(Todo)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_noRequestBodyEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODOS_BASE_URI))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Faulty Request Body.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#create(Todo)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_noRequestBodyGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODOS_BASE_URI)
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Το σώμα του αιτήματος είναι εσφαλμένο.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#create(Todo)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_mediaTypeNotSupportedGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODOS_BASE_URI)
            .header("Accept-Language", "el-GR")
            .content("{}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Ο τύπος του περιεχομένου δεν υποστηρίζεται.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#create(Todo)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_mediaTypeNotSupportedEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODOS_BASE_URI).content("{}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"The content type is not supported.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoController#create(Todo)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_JustUserIdEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODOS_BASE_URI)
            .content("{\"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"todoListId\":\"ID should not be empty.\",\"title\":\"Title should not be empty.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }


  /**
   * test for {@link TodoController#create(Todo)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_JustUserIdGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODOS_BASE_URI)
            .header("Accept-Language", "el-GR")
            .content("{\"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"todoListId\":\"Το ID δεν μπορεί να είναι κενό.\",\"title\":\"Ο τίτλος δεν μπορεί να είναι κενός.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}
