#!/bin/sh
# working root(change)
WK_PATH=.
#jar name
JAR=${project.artifactId}-${project.version}.jar
#pid file
PID_FILE=${WK_PATH}/${project.artifactId}.pid
#pid
PID=

if [ ! -n "$WK_PATH" ];then
    echo "[ERROR] please set up the work path : WK_PATH"
    exit
fi

for LINE in `cat ${PID_FILE}`
  do
   PID="$PID$LINE"
  done

G_PID=`ps -ef | grep ${JAR} |grep -v 'grep' |awk '{print $2}'`

if [ ! -n "$PID" ];then
    echo "[ERROR] pid not exists"
elif [ ! -n "$G_PID" ];then
    echo "[ERROR] process not exists"
else
  kill ${PID}
    if [ $? -ne 0 ];then
        echo "[ERROR] service shutdown failed."
        exit
    else
        echo "kill pid:[$PID]"
        echo "[INFO] service shutdown success."
    fi
fi

if [ -w "$PID_FILE" ];then
    `rm -rf ${PID_FILE}`
fi
