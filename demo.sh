#!/usr/bin/env bash

echo "截取第一个字符串${1:1:4}"
echo "第一个字符串长度${#1}"

echo "显示文件名称 ${0}"
echo "显示所有参数个数 ${#}"
echo "显示所有参数 ${*}"

if [[ -z "${1}" ]]; then
    echo "第一个参数为空"
fi

echo "显示shell最后退出的状态 ${?}"