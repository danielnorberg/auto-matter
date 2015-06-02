FROM ubuntu:14.10
MAINTAINER Daniel Norberg <daniel.norberg@gmail.com>

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get install -y software-properties-common
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update

# Auto-accept the Oracle JDK license
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections

RUN apt-get install -y openjdk-7-jdk openjdk-8-jdk oracle-java7-installer oracle-java8-installer maven
RUN apt-get install -y curl jq

RUN mkdir -p /usr/src/auto-matter
WORKDIR /usr/src/auto-matter

ADD . /usr/src/auto-matter
RUN mvn -B install -DskipTests

# OpenJDK 7
RUN update-alternatives --set java /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java
RUN update-alternatives --set javac /usr/lib/jvm/java-7-openjdk-amd64/bin/javac
RUN mvn -B clean verify
RUN ./jackson-it.sh

# OpenJDK 8
RUN update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
RUN update-alternatives --set javac /usr/lib/jvm/java-8-openjdk-amd64/bin/javac
RUN mvn -B clean verify
RUN ./jackson-it.sh

# Oracle JDK 7
RUN update-alternatives --set java /usr/lib/jvm/java-7-oracle/jre/bin/java
RUN update-alternatives --set javac /usr/lib/jvm/java-7-oracle/bin/javac
RUN mvn -B clean verify
RUN ./jackson-it.sh

# Oracle JDK 8
RUN update-alternatives --set java /usr/lib/jvm/java-8-oracle/jre/bin/java
RUN update-alternatives --set javac /usr/lib/jvm/java-8-oracle/bin/javac
RUN mvn -B clean verify
RUN ./jackson-it.sh
