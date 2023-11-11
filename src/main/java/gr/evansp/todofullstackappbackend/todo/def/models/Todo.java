package gr.evansp.todofullstackappbackend.todo.def.models;

import gr.evansp.todofullstackappbackend.common.beans.BodyOwner;
import gr.evansp.todofullstackappbackend.common.beans.CreationDateOwner;
import gr.evansp.todofullstackappbackend.common.beans.ModificationDateOwner;
import gr.evansp.todofullstackappbackend.common.beans.TitleOwner;

/**
 * Application Todo1.
 */
public interface Todo extends TodoPK, TitleOwner, BodyOwner, CreationDateOwner, ModificationDateOwner {
  //EMPTY
}
