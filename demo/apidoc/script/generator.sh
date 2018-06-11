#!/bin/sh
# 工作路径（根目录）
WK_PATH=../..
TARGET_PATH=${WK_PATH}/dubbo-springboot-demo/dubbo-springboot-demo-client/src/main/resources/static/apidoc
apidoc -i ${WK_PATH} -o  ${TARGET_PATH} -t ${WK_PATH}/apidoc/template