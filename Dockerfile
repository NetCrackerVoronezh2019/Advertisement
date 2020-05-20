FROM openjdk:11
ADD target/AdvertisementMicroservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 1122
ENTRYPOINT ["java","-jar","app.jar"]