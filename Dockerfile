FROM amazoncorretto:16
ENV GRADLE_HOME /opt/gradle
ENV GRADLE_VERSION 7.5.1
RUN yum -y update && yum -y install unzip curl
RUN mkdir $GRADLE_HOME \
  && curl -L https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip \
  && unzip gradle.zip -d $GRADLE_HOME \
  && rm gradle.zip
ENV PATH $PATH:$GRADLE_HOME/gradle-${GRADLE_VERSION}/bin
WORKDIR /app
COPY . /app
CMD ["gradle", "html:superDev"]