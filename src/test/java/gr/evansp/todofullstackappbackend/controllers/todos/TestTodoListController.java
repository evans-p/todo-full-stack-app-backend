package gr.evansp.todofullstackappbackend.controllers.todos;


import gr.evansp.todofullstackappbackend.base.BaseITTest;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import gr.evansp.todofullstackappbackend.samples.Samples;
import gr.evansp.todofullstackappbackend.services.todos.TodoListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Tests for {@link TodoListController}
 */
@SuppressWarnings("nls")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TestTodoListController extends BaseITTest {
  /**
   * {@link TodoListService}.
   */
  @Autowired
  TodoListService todoListService;

  /**
   * {@link TodoListService}.
   */
  @Autowired
  TodoListRepository todoListRepository;

  /**
   * {@link MockMvc}.
   */
  @Autowired
  MockMvc mockMvc;

  /**
   * test for {@link TodoListController#readAll()}.
   *
   * @throws Exception Exception
   */
  @Test
  void testReadAll_ForbiddenGreek() throws Exception {
    SecurityContextHolder.clearContext();
    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI)
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isForbidden())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Δεν έχετε πρόσβαση.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#readAll()}.
   *
   * @throws Exception Exception.
   */
  @Test
  void testReadAll_ForbiddenEnglish() throws Exception {
    SecurityContextHolder.clearContext();
    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI))
        .andExpect(status().isForbidden())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Access denied.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#readAll()}.
   *
   * @throws Exception Exception.
   */
  @Test
  void testReadAll_EmptyList() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/hal+json"))
        .andExpect(content().bytes("{\"_links\":{\"self\":{\"href\":\"http://localhost/todo-lists/\"}}}".getBytes()));
  }

  /**
   * test for {@link TodoListController#readAll()}.
   *
   * @throws Exception Exception.
   */
  @Test
  void testReadAll_ok() throws Exception {
    TodoList list = todoListService.store(Samples.createSampleTodoList(SUB));

    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/hal+json"));

    todoListService.delete(list.getTodoListId());
  }

  /**
   * test for {@link TodoListController#read(Long)}
   *
   * @throws Exception Exception.
   */
  @Test
  void testRead_badRequestGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI + "/asd")
            .header("Accept-Language", "el-GR"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().bytes("{\"message\":\"Το αίτημα δεν μπορεί να διεκπεραιωθεί.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#read(Long)}
   *
   * @throws Exception Exception.
   */
  @Test
  void testRead_badRequestEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI + "/asd"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Request can't be processed.\",\"messages\":null}".getBytes()));
  }

  /**
   * Bad URL
   *
   * @throws Exception Exception
   */
  @Test
  void testBadURL_greek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/asd")
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content()
            .bytes("{\"message\":\"Δεν βρέθηκε περιεχόμενο στο συγκεκριμένο URL.\",\"messages\":null}".getBytes()));
  }

  /**
   * Bad URL
   *
   * @throws Exception Exception
   */
  @Test
  void testBadURL_english() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/asd"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content()
            .bytes("{\"message\":\"No content found at the specified URL.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_notFoundEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI + "/-1"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"List was not found.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_notFoundGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI + "/-1")
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Η λίστα δεν βρέθηκε.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_unauthorizedGreek() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList("asd"));

    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI + "/" + list.getTodoListId().toString())
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isForbidden())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Δεν έχετε πρόσβαση.\",\"messages\":null}".getBytes()));
    todoListRepository.delete(list);
  }

  /**
   * test for {@link TodoListController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_unauthorizedEnglish() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList("asd"));

    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI + "/" + list.getTodoListId().toString()))
        .andExpect(status().isForbidden())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Access denied.\",\"messages\":null}".getBytes()));
    todoListRepository.delete(list);
  }

  /**
   * test for {@link TodoListController#read(Long)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testFind_ok() throws Exception {
    TodoList list = todoListService.store(Samples.createSampleTodoList(SUB));

    mockMvc.perform(MockMvcRequestBuilders.get(TODO_LISTS_BASE_URI + "/" + list.getTodoListId().toString()))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/hal+json"));
    todoListRepository.delete(list);
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_noRequestBodyEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Faulty Request Body.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_noRequestBodyGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Το σώμα του αιτήματος είναι εσφαλμένο.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_mediaTypeNotSupportedGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .header("Accept-Language", "el-GR")
            .content("{}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Ο τύπος του περιεχομένου δεν υποστηρίζεται.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_mediaTypeNotSupportedEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI).content("{}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"The content type is not supported.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_EmptyTitleEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .content("{\"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"title\":\"Title should not be empty.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_EmptyTitleGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .content("{\"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Accept-Language", "el-GR"))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"title\":\"Ο τίτλος δεν μπορεί να είναι κενός.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_EmptyUserIdEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\"}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"userId\":\"User ID should not be empty.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_EmptyUserIdGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Accept-Language", "el-GR"))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"userId\":\"Το ID του χρήστη δεν μπορεί να είναι κενό.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_alreadyExistsGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\", \"userId\": 1, \"todoListId\": 1}")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isBadRequest())
        .andExpect(content().bytes("{\"message\":\"lalala υπάρχει ήδη.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_alreadyExistsEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\", \"userId\": 1, \"todoListId\": 1}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().bytes("{\"message\":\"lalala already exists.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_notTheOwner() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\", \"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden())
        .andExpect(content().bytes("{\"message\":\"Access denied.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testCreate_ok() throws Exception {
    String content = "{\"title\": \"lalala\", \"userId\":" + "\"" +
        SUB +
        "\"" +
        "}";
    mockMvc.perform(MockMvcRequestBuilders.post(TODO_LISTS_BASE_URI)
            .content(content)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType("application/hal+json"))
        .andExpect(status().isOk());

    List<TodoList> lists = todoListService.getAll();

    for (TodoList list : lists) {
      todoListService.delete(list.getTodoListId());
    }
  }


  /**
   * test for {@link TodoListController#delete(Long)}
   *
   * @throws Exception Exception
   */
  @Test
  void testDelete_noListId() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete(TODO_LISTS_BASE_URI)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isMethodNotAllowed())
        .andExpect(content().bytes("{\"message\":\"Method not supported.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#delete(Long)}
   *
   * @throws Exception Exception
   */
  @Test
  void testDelete_badRequestUrl() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete(TODO_LISTS_BASE_URI + "/asdsad")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Request can't be processed.\",\"messages\":null}".getBytes()))
        .andExpect(status().isBadRequest());
  }

  /**
   * test for {@link TodoListController#delete(Long)}
   *
   * @throws Exception Exception
   */
  @Test
  void testDelete_notFound() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete(TODO_LISTS_BASE_URI + "/1"))
        .andExpect(status().isNotFound())
        .andExpect(content().bytes("{\"message\":\"List was not found.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#delete(Long)}
   *
   * @throws Exception Exception
   */
  @Test
  void testDelete_notFoundGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete(TODO_LISTS_BASE_URI + "/1")
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isNotFound())
        .andExpect(content().bytes("{\"message\":\"Η λίστα δεν βρέθηκε.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#delete(Long)}
   *
   * @throws Exception Exception
   */
  @Test
  void testDelete_forbidden() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList("laalal"));
    mockMvc.perform(MockMvcRequestBuilders.delete(TODO_LISTS_BASE_URI + list.getTodoListId())
            .header("Accept-Language", "el-GR"))
        .andExpect(content().bytes("{\"message\":\"Δεν έχετε πρόσβαση.\",\"messages\":null}".getBytes()))
        .andExpect(status().isForbidden());
    todoListRepository.delete(list);
  }

  /**
   * test for {@link TodoListController#delete(Long)}
   *
   * @throws Exception Exception
   */
  @Test
  void testDelete_ok() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    mockMvc.perform(MockMvcRequestBuilders.delete(TODO_LISTS_BASE_URI + list.getTodoListId())
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isOk());
    assertEquals(0, todoListRepository.findAll().size());
  }


  /**
   * test for {@link TodoListController#update(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_noRequestBodyEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Faulty Request Body.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#update(TodoList)}
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_noRequestBodyGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Το σώμα του αιτήματος είναι εσφαλμένο.\",\"messages\":null}".getBytes()));
  }


  /**
   * test for {@link TodoListController#update(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_mediaTypeNotSupportedGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .header("Accept-Language", "el-GR")
            .content("{}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Ο τύπος του περιεχομένου δεν υποστηρίζεται.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#update(TodoList)}
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_mediaTypeNotSupportedEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI).content("{}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"The content type is not supported.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#update(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_EmptyTitleEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"title\":\"Title should not be empty.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  /**
   * test for {@link TodoListController#update(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_EmptyTitleGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Accept-Language", "el-GR"))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"title\":\"Ο τίτλος δεν μπορεί να είναι κενός.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  /**
   * test for {@link TodoListController#update(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_EmptyUserIdEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\"}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"userId\":\"User ID should not be empty.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  /**
   * test for {@link TodoListController#update(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_EmptyUserIdGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Accept-Language", "el-GR"))
        .andExpect(content().bytes(
            "{\"message\":null,\"messages\":{\"userId\":\"Το ID του χρήστη δεν μπορεί να είναι κενό.\"}}".getBytes()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }


  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_doesNotExistGreek() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\", \"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Accept-Language", "el-GR"))
        .andExpect(status().isBadRequest())
        .andExpect(content().bytes("{\"message\":\"lalala δεν υπάρχει.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_doesNotExistEnglish() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\", \"userId\": 1}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().bytes("{\"message\":\"lalala does not exist.\",\"messages\":null}".getBytes()));
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_forbidden1() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\", \"userId\": \"1\", \"todoListId\": " + list.getTodoListId() + "}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Access denied.\",\"messages\":null}".getBytes()))
        .andExpect(status().isForbidden());
    todoListRepository.delete(list);
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_forbidden2() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList("asd"));
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\", \"userId\": \"" + SUB + "\", \"todoListId\": " + list.getTodoListId() + "}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().bytes("{\"message\":\"Access denied.\",\"messages\":null}".getBytes()))
        .andExpect(status().isForbidden());
    todoListRepository.delete(list);
  }

  /**
   * test for {@link TodoListController#create(TodoList)}.
   *
   * @throws Exception Exception
   */
  @Test
  void testUpdate_ok() throws Exception {
    TodoList list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    mockMvc.perform(MockMvcRequestBuilders.put(TODO_LISTS_BASE_URI)
            .content("{\"title\": \"lalala\", \"userId\": \"" + SUB + "\", \"todoListId\": " + list.getTodoListId() + "}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    todoListRepository.delete(list);
  }

}