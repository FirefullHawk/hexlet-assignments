FROM gradle:8.3.0-jdk20

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./app $APP_HOME
RUN gradle clean build
EXPOSE 8080

ENTRYPOINT ["gradle", "bootRun"]