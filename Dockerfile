FROM openjdk:11
COPY ./backend/target/lib backend/lib
ADD ./backend/target/pessoas-0.0.1-SNAPSHOT.jar backend/pessoas-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/backend/pessoas-0.0.1-SNAPSHOT.jar"]
