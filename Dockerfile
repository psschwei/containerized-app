FROM psschwei/spark:2.4.5
RUN set -ex && apt-get update && apt-get upgrade -y && rm -rf /var/cache/apt/*

COPY target /opt/spark/target

ENV SPARK_HOME /opt/spark
WORKDIR /opt/spark/work-dir
ENTRYPOINT [ "/opt/entrypoint.sh" ]
