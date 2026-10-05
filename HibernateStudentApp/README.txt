Hibernate Student Maven Application

Run:
mvn clean compile
mvn exec:java

Verify in MySQL:
SELECT * FROM student;

Final record should have id=101, name=Kalai, email=kalai@gmail.com, course=Java Full Stack.

Do not commit real database credentials to public repositories.
