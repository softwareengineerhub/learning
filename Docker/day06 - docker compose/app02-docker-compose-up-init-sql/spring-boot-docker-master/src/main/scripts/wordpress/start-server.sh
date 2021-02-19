#!/bin/bash

if [[ -z "$UGP_ENVIRONMENT" ]]; then
  echo "Missing UGP_ENVIRONMENT variable."
  exit 1
fi
if [[ -z "$BASE_LOGGING_LEVEL" ]]; then
  BASE_LOGGING_LEVEL="WARN"
fi

JAVA_DEBUG="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
JAVA_DEFINES="-Dfile.encoding=UTF-8 -Djava.awt.headless=true -DLOG_ROOT=/var/log/electro -Dlogback.configurationFile=config/logback.xml -DBASE_LOGGING_LEVEL=$BASE_LOGGING_LEVEL"
JAVA_JMX="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=$JMX_PORT -Dcom.sun.management.jmxremote.rmi.port=$RMI_PORT -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=$HOST_IP"
JAVA_MEM="-Xms$MIN_MEMORY -Xmx$MAX_MEMORY -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/electro"
JAVA_CP="-cp /opt/electro/lib/*:/opt/electro/config/"
JAVA_OPTS="-server -XX:-OmitStackTraceInFastThrow $JAVA_MEM $JAVA_DEBUG $JAVA_DEFINES $JAVA_JMX $JAVA_CP"

if [[ -n "$UGP_SERVER_GROUPS" ]]; then
  JAVA_DEFINES=$JAVA_DEFINES -Drouter.server_groups=$UGP_SERVER_GROUPS
fi

cd /opt/electro
/sbin/setuser ugp echo Starting at `date` >> /var/log/electro/out.log

if [[ "x$STDOUT_LOGGING" == "x" ]]; then
  exec /sbin/setuser ugp java $JAVA_EOPTS $JAVA_OPTS com.h5g.ugp.utility.cli.CommandLineRunner -ugp.environment $UGP_ENVIRONMENT $EARGS -overlayConfig /opt/config/config-overrides.ini 2>&1 >> /var/log/electro/out.log
else
  exec /sbin/setuser ugp java $JAVA_EOPTS $JAVA_OPTS com.h5g.ugp.utility.cli.CommandLineRunner -ugp.environment $UGP_ENVIRONMENT $EARGS -overlayConfig /opt/config/config-overrides.ini
fi
