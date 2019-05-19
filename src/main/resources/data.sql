INSERT INTO author(id, first_name, last_name, age)
VALUES (1, "Joseph", "Stan", 35),
       (2, "Ann", "Novacic", 25),
       (3, "Andrew", "Wank", 22),
       (4, "Giuseppa", "Forken", 45),
       (5, "Vlad", "Stankiew", 19),
       (6, "Katarina", "Cendry", 47);

INSERT INTO post(id, title, content, publish_date, author_id)
VALUES (1, "Java 8 news", "Example content...", "2019-01-01", 1),
       (2, "Interview questions", "Top 100 java interview questions with detailed answers ", "2019-03-12", 1),
       (3, "Interface vs abstract class", "What is difference between abstract class and interface..", "2018-11-05", 3),
       (4, "Diamons problem", "Dealing with diamond problem in java...", "2017-07-15", 6),
       (5, "JavaScript", "An introduction to JavaScript", "2019-02-27", 1),
       (6, "HTML5 tutorial", "HTMl5 course for beginners", "2019-01-01", 4);
