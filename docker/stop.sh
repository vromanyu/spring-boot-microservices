#!/usr/bin/env bash

set -e

printf "The script depends on the following environment variables\n\
POSTGRES_USER\n\
POSTGRES_PASSWORD\n\
POSTGRES_DB\n\
SERVER_PORT\n\
SPRING_PROFILES_ACTIVE\n"

if [ -z "${POSTGRES_USER}" ] || [ -z "${POSTGRES_PASSWORD}" ] \
|| [ -z "${POSTGRES_DB}" ] || [ -z "${SERVER_PORT}" ] || [ -z "${SPRING_PROFILES_ACTIVE}" ]; then
  echo "not all variables are set"
  exit 1
fi

echo "Stoping docker compose..."

docker-compose down