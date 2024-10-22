# About
Simple CRUD application using Spring Framework with JdbcTemplate. Some examples of using Hibernate Validator are presented here, along with error printing.
### Working links
+ library/books/ - the main page for books management. A list of existing books in the database is presented, along with a button to create a new book.
+ library/books/[id] - page for viewing a book. Here, you can assign the selected book to a person, release it, delete it, or edit it.
+ library/books/[id]/edit - page for updating a book.
+ library/people/ - the main page for managing people profiles. Here, you can view the list of people and create a new profile.
+ library/people/[id] - page for viewing a person profile, where you can delete it or edit it. The assigned book (if there is one) and a link to that book are also presented here.
+ library/people/[id]/edit - page for updating a person profile.

### Program setup
At src/main/resources, you can find the file database.properties, where you need to fill in the parameters for the database connection, and database.sql for database creation. 