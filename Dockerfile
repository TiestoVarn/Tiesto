FROM java:8
EXPOSE 8080
ADD /target/petclinic.jar petclinic.jar
ENTRYPOINT ["java","-jar","petclinic.jar"]