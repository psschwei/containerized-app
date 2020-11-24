# docker build -t us.icr.io/oia-repo/spark-submit:tekton -f s2i.Dockerfile --build-arg USER=$ARTIFACTORY_USER --build-arg KEY=$ARTIFACTORY_API_KEY .

FROM openjdk:8

ENV SBT_VERSION=1.4.3

RUN \
  curl -L -o sbt-$SBT_VERSION.deb http://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt

RUN mkdir /wkdir

COPY . /wkdir

RUN cd /wkdir && sbt assembly

FROM psschwei/spark:2.4.5
RUN set -ex && apt-get update && apt-get upgrade -y && rm -rf /var/cache/apt/*

COPY --from=0 /wkdir/target /opt/spark/target

ENV SPARK_HOME /opt/spark
WORKDIR /opt/spark/work-dir
ENTRYPOINT [ "/opt/entrypoint.sh" ]
