#!/bin/sh

WK_PATH=../..
TARGET_PATH=${WK_PATH}/biz/core/src/main/resources/static/apidoc
apidoc -i ${WK_PATH} -o  ${TARGET_PATH} -t ${WK_PATH}/apidoc/template