#!/bin/sh
set -e
usage="Usage: $0 [-RUNTIME_ENV] (prod|staging|readFileByByte|integration) (default \"prod\") [-SEVER_PORT] <port> (default \"8080\") [-JAR_FILE] <path-to-jarfile>  (default \"/app.jar\")"
echo "all param is ${*}"
while [[ $# -gt 0 ]]
do
    case "$1" in 
        -RUNTIME_ENV)
            RUNTIME_ENV=$2;
            shift
            ;;
        -SERVER_PORT)
            SERVER_PORT=$2;
            shift
            ;;
        -JAR_FILE)
            JAR_FILE=$2;
            shift
            ;;
        -jaeger_host)
            jaeger_host=$2;
            shift
            ;;
        --)
            shift;
            break;;
        -*)
            echo >&2 "$usage"
            exit 1
            ;;
        *)
            echo >&2 "$usage"
            exit 1
            ;;
    esac
    shift
done
echo ${JAR_FILE}

[[ -z ${JAR_FILE} ]] && JAR_FILE=/app.jar
[[ -z ${RUNTIME_ENV} ]] && RUNTIME_ENV=prod
[[ -z ${SERVER_PORT} ]] && SERVER_PORT=8080

startupCMD="java -Djava.security.edg=file:/dev/./urandom -jar $JAR_FILE --server.port=$SERVER_PORT --debug=false --spring.profiles.active=$RUNTIME_ENV"

if [[ ${#jaeger_host} -gt 0 ]]; then
    echo ${jaeger_host}
    startupCMD=${startupCMD}"--jaeger.host="${jaerger_host}
    echo ${startupCMD}
fi
echo ${startupCMD}
exec ${startupCMD}

