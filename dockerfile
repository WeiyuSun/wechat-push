FROM openjdk:11

COPY *.jar /app.jar

CMD ["--server.port=5200"]

EXPOSE 5200

ENTRYPOINT ["java","-jar","/app.jar"]
