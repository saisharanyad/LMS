CREATE TABLE IF NOT EXISTS `books` (
  `book_id` int AUTO_INCREMENT  PRIMARY KEY,
  `author` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `published_year` varchar(10) NOT NULL,
  `availability_status` varchar(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int AUTO_INCREMENT  PRIMARY KEY,
  `email_id` varchar(100) NOT NULL,
  `username` varchar(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS `wishlist_users` (
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
   PRIMARY KEY(user_id,book_id),
   FOREIGN KEY (user_id) REFERENCES  users(user_id) ON DELETE CASCADE,
   FOREIGN KEY (book_id) REFERENCES  books(book_id) ON DELETE CASCADE
);
