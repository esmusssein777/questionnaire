FROM java:8
COPY build/libs/questionnaire-1.0.0.jar /questionnaire.jar
RUN bash -c 'touch /questionnaire.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/questionnaire.jar"]
MAINTAINER guangzheng.li