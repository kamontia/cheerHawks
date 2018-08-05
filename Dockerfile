FROM hseeberger/scala-sbt:8u171-2.12.6-1.2.0
MAINTAINER Takeshi Kondo
COPY . /usr/local/src/
WORKDIR /usr/local/src/
EXPOSE 9000
RUN sbt run
