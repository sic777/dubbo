#!/bin/sh
# working root(change)
WK_PATH=.
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

#pid file path
PID_FILE_PATH=${WK_PATH}/${project.artifactId}.pid

if [ ! -n "$WK_PATH" ];then
    echo "please set up the work path : [WK_PATH]"
    exit
fi

G_PID=`ps -ef | grep ${JAR} |grep -v 'grep' |awk '{print $2}'`
if [ -n "$G_PID" ];then
    echo "process exists"
    exit
fi

cd ${WK_PATH}

/usr/bin/nohup java -Denvironment=${ENVIRONMENT} -Dlog4j.configurationFile=${WK_PATH}/config/log4j2.xml ${JVM_OPT} -cp ${WK_PATH}/lib/${JAR}:${WK_PATH}/lib/* ${MAIN_CLASS} ${ARGS} >/dev/null 2>&1 &

sleep 3s;

PID=`ps -ef | grep ${JAR} |grep -v 'grep' |awk '{print $2}'`


echo ${PID} > ${PID_FILE_PATH}

echo "service startup success."
