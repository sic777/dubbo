#!/bin/sh
# working root(change)
WK_PATH=.
#java home
JAVA_HOME=
# main class args
ARGS=${StartArgs}
#jvm option
JVM_OPT="-Xmx512M -Xms512M -server -XX:+PrintGCDetails -XX:+PrintGCDateStamps"
#jar name
JAR=${project.artifactId}-${project.version}.jar
#main class
MAIN_CLASS=${MainClass}
#environment
ENVIRONMENT=${profiles.environment}
#java command
JAVA_CMD=`which java`
#pid file
PID_FILE=${WK_PATH}/${project.artifactId}.pid


if [ ! -n "$WK_PATH" ];then
    echo "[ERROR] please set up the work path : WK_PATH"
    exit
fi

if [ -n "$JAVA_HOME" ];then
    JAVA_CMD=${JAVA_HOME}/bin/java
fi

G_PID=`ps -ef | grep ${JAR} |grep -v 'grep' |awk '{print $2}'`
if [ -n "$G_PID" ];then
    echo "[ERROR] process exists,pid:"${G_PID}
    exit
fi

cd ${WK_PATH}

if [ ! -f  "$PID_FILE" ];then
    touch ${PID_FILE}
else
    if [ ! -w "$PID_FILE" ];then
        echo "Permission denied"
        exit
    fi
fi

if [ $? -ne 0 ];then
    exit
fi


${JAVA_CMD} -version

/usr/bin/nohup ${JAVA_CMD} -Denvironment=${ENVIRONMENT} -Dlog4j.configurationFile=${WK_PATH}/config/log4j2.xml ${JVM_OPT} -cp ${WK_PATH}/lib/${JAR}:${WK_PATH}/lib/* ${MAIN_CLASS} ${ARGS} >/dev/null 2>&1 &

if [ $? -ne 0 ];then
    exit
fi

PID=`ps -ef | grep ${JAR} |grep -v 'grep' |awk '{print $2}'`

if [ ! -n "$PID" ];then
    echo "[ERROR] service startup failed"
    `rm -rf ${PID_FILE}`
    exit
else
    echo "[INFO] service startup success,pid:"${PID}
    echo ${PID} > ${PID_FILE}
fi
