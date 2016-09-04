FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y software-properties-common
RUN apt-get update
RUN apt-get install -y wget build-essential git

# Install Java.
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
rm -rf /var/cache/oracle-jdk8-installer

# Define working directory.
WORKDIR /data

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Install Nodejs.
RUN wget -qO- https://deb.nodesource.com/setup_4.x | bash -
RUN apt-get update
RUN apt-get -y install nodejs

# Git clone repo

#EXPOSE 6379
#ENTRYPOINT  ["javafx_example"]
#CMD ["bash"]
