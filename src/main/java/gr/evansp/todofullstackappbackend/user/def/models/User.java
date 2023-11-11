package gr.evansp.todofullstackappbackend.user.def.models;

import gr.evansp.todofullstackappbackend.common.beans.CreationDateOwner;
import gr.evansp.todofullstackappbackend.common.beans.EmailOwner;
import gr.evansp.todofullstackappbackend.common.beans.PasswordOwner;

import java.io.Serializable;

/**
 * Application User.
 */
public interface User extends UserPK, EmailOwner, PasswordOwner, CreationDateOwner, Serializable {
  //EMPTY
}
