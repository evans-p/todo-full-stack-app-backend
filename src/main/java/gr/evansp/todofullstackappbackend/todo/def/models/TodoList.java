package gr.evansp.todofullstackappbackend.todo.def.models;

import gr.evansp.todofullstackappbackend.common.beans.CreationDateOwner;
import gr.evansp.todofullstackappbackend.common.beans.ModificationDateOwner;
import gr.evansp.todofullstackappbackend.common.beans.TitleOwner;
import gr.evansp.todofullstackappbackend.common.beans.TodoSetOwner;

/**
 * Todos List.
 */
public interface TodoList extends TodoListPK, TitleOwner, TodoSetOwner, CreationDateOwner, ModificationDateOwner {
  //EMPTY
}
