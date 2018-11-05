#FROM openjdk:8-jdk-alpine
#FROM mherwig/alpine-java-mongo

FROM alpine:edge

RUN apk add --no-cache bash

RUN apk add --no-cache openjdk8

RUN echo '@testing http://dl-4.alpinelinux.org/alpine/edge/testing' >> /etc/apk/repositories && \
  apk add --no-cache mongodb@testing
#  #&& rm /usr/bin/mongosniff /usr/bin/mongoperf

VOLUME ["/data/db"]

CMD [ "mongod" ]

RUN apk add --no-cache mongodb-tools

COPY ./database/taxCategory.json /taxCategory.json

RUN mongoimport --host host.docker.internal --db tax-calc --collection taxCategory --drop --file /taxCategory.json

RUN export spring_profiles_active=prod

VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","-Dspring.profiles.active=prod","taxcalc.Application"]