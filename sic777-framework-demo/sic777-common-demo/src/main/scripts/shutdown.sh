#!/bin/sh
#1. 请配置工作路径为根目录（必填）
WK_PATH=
#2. JAR包全称（必填）
JAR=

PID_FILE_PATH=$WK_PATH/pid/pid
PID=""

if [ ! -n "$WK_PATH" ];then
    echo "please set up the work path : [WK_PATH]"
    exit
fi

for LINE in `cat $PID_FILE_PATH`
  do
   PID="$PID$LINE"
  done

G_PID=`ps -ef | grep $JAR |grep -v 'grep' |awk '{print $2}'`

if [ ! -n "$PID" ];then
  echo "PID not exists"
  exit
elif [ ! -n "$G_PID" ];then
  echo "process not exists"
  echo "">$PID_FILE_PATH
  exit
elif [ "$PID" != "$G_PID" ];then
  echo "PID error"
  echo "">$PID_FILE_PATH
  exit
else
#  echo "kill proceess pid:[$PID]"
  kill $PID
  echo "">$PID_FILE_PATH
  echo "Service shutdown success."
fi
