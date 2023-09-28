FROM gradle:8.3.0-jdk20

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./app $APP_HOME
EXPOSE 3000
ENTRYPOINT ["gradle", "bootRun"]
RUN gradle clean build



