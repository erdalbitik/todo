FROM ubuntu:latest

RUN apt-get update && apt-get -y upgrade
RUN apt-get -y install software-properties-common
RUN add-apt-repository ppa:webupd8team/java

RUN apt-get update -y

#add git client
RUN apt-get install --no-install-recommends -qqy \
    curl \
    ca-certificates \
	git

# Accept the license
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections
RUN apt-get -y install oracle-java8-installer

# Define JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
 
 # Install maven 3.5.3
RUN wget --no-verbose -O /tmp/apache-maven-3.5.3-bin.tar.gz http://www-eu.apache.org/dist/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.tar.gz && \
 tar xzf /tmp/apache-maven-3.5.3-bin.tar.gz -C /opt/ && \
 ln -s /opt/apache-maven-3.5.3 /opt/maven && \
 ln -s /opt/maven/bin/mvn /usr/local/bin && \
 rm -f /tmp/apache-maven-3.5.3-bin.tar.gz 
 
ENV MAVEN_HOME /opt/maven

RUN git clone https://github.com/erdalbitik/todo.git /opt/todo

CMD ["mvn", "-f", "/opt/todo/pom.xml", "spring-boot:run"]
