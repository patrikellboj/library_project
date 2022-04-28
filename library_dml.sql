USE library_db;

INSERT INTO category (category_name) VALUES ('Fantasy');
INSERT INTO category (category_name) VALUES ('Comic');
INSERT INTO category (category_name) VALUES ('Action');
INSERT INTO category (category_name) VALUES ('Romance');
INSERT INTO category (category_name) VALUES ('Adventure');
INSERT INTO category (category_name) VALUES ('Thriller');
INSERT INTO category (category_name) VALUES ('Horror');

INSERT INTO library_item (
  category_id, 
  title, 
  author, 
  pages, 
  borrowable,
  `type`
  ) 
VALUES (
  1, 
  'Don Quixote', 
  'Miguel De Cervantes', 
  211, 
  true, 
  'BOOK'
  );

INSERT INTO library_item (
  category_id, 
  title, 
  author, 
  pages, 
  borrowable, 
  `type`
  ) 
VALUES (
  2, 
  'Pilgrims Progress', 
  'John Bunyan', 
  325, 
  true, 
  'BOOK'
  );

INSERT INTO library_item (
  category_id, 
  title, 
  run_time_minutes, 
  borrowable, 
  `type`
  ) 
VALUES (
  5, 
  'Shawshank redemption',
  132, 
  true, 
  'DVD'
  );

INSERT INTO library_item (
  category_id, 
  title, 
  run_time_minutes, 
  borrowable, 
  `type`
  ) 
VALUES (
  3, 
  'Die Hard',
  123, 
  true, 
  'DVD'
  );

INSERT INTO library_item (
  category_id, 
  title, 
  run_time_minutes, 
  borrowable, 
  `type`
  ) 
VALUES (
  3, 
  'The wind in the willows',
  426, 
  true, 
  'AUDIO_BOOK'
  );

INSERT INTO library_item (
  category_id, 
  title, 
  run_time_minutes, 
  borrowable, 
  `type`
  ) 
VALUES (
  5, 
  'Harry Potter and the Sorcerers Stone',
  534, 
  true, 
  'AUDIO_BOOK'
  );

INSERT INTO library_item (
  category_id, 
  title, 
  author, 
  pages, 
  borrowable, 
  `type`
  ) 
VALUES (
  3, 
  'Carrie',
  'Stephen King',
  427, 
  false, 
  'REFERENCE_BOOK'
  );

INSERT INTO library_item (
  category_id, 
  title, 
  author, 
  pages, 
  borrowable, 
  `type`
  ) 
VALUES (
  4, 
  'You and Me',
  'Roald Dahl',
  120, 
  false, 
  'REFERENCE_BOOK'
  );
