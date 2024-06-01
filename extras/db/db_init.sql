CREATE SCHEMA TODO;

-- Drop previously created tables.
DROP TABLE IF EXISTS TODO.TBTODOLIST;
DROP TABLE IF EXISTS TODO.TBTODO;


-- TodoList
CREATE TABLE TODO.TBTODOLIST (
    LIST_ID SERIAL NOT NULL,
    USER_ID VARCHAR(256) NOT NULL,
    TITLE VARCHAR(256) NOT NULL,
    CREATED TIMESTAMP NOT NULL,
    LAST_MODIFIED TIMESTAMP NOT NULL
);

-- TodoList
CREATE TABLE TODO.TBTODO (
    TODO_ID SERIAL NOT NULL,
    LIST_ID BIGINT NOT NULL,
    USER_ID VARCHAR(256) NOT NULL,
    TITLE VARCHAR(256) NOT NULL,
    BODY VARCHAR(256),
    CREATED TIMESTAMP NOT NULL,
    LAST_MODIFIED TIMESTAMP NOT NULL,
    FAVOURITE BOOLEAN,
    COMPLETED BOOLEAN
);

-- Primary keys
ALTER TABLE TODO.TBTODOLIST ADD CONSTRAINT PKTBTODOLIST PRIMARY KEY(LIST_ID);
ALTER TABLE TODO.TBTODO ADD CONSTRAINT PKTBTODO PRIMARY KEY(TODO_ID);

-- Foreign keys
ALTER TABLE TODO.TBTODO ADD CONSTRAINT FKTODOLIST FOREIGN KEY(LIST_ID) REFERENCES TODO.TBTODOLIST(LIST_ID) ON DELETE CASCADE;
