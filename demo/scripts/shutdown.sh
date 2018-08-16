#!/bin/sh
# working root(change)
WK_PATH=.
#jar name
JAR=${project.artifactId}-${project.version}.jar
#pid file path
PID_FILE_PATH=${WK_PATH}/${project.artifactId}.pid
#pid
PID=""

if [ ! -n "$WK_PATH" ];then
    echo "please set up the work path : [WK_PATH]"
    exit
fi

for LINE in `cat ${PID_FILE_PATH}`
  do
   PID="$PID$LINE"
  done

G_PID=`ps -ef | grep ${JAR} |grep -v 'grep' |awk '{print $2}'`

if [ ! -n "$PID" ];then
  echo "pid not exists"
  exit
elif [ ! -n "$G_PID" ];then
  echo "process not exists"
  echo "">${PID_FILE_PATH}
  exit
elif [ "$PID" != "$G_PID" ];then
  echo "pid error"
  echo "">${PID_FILE_PATH}
  exit
else
#  echo "kill proceess pid:[$PID]"
  kill ${PID}
  echo "">${PID_FILE_PATH}
  echo "service shutdown success."
fi
