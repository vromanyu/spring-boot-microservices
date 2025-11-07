#!/usr/bin/env bash


printf "The script depends on the following environment variables\n\
POSTGRES_USER\n\
POSTGRES_PASSWORD\n\
POSTGRES_DB\n\
SERVER_PORT\n"

if [ -z "${POSTGRES_USER}" ] || [ -z "${POSTGRES_PASSWORD}" ] \
|| [ -z "${POSTGRES_DB}" ] || [ -z "${SERVER_PORT}" ]; then
  echo "not all variables are set"
  exit 1
fi

echo "Starting docker compose..."

docker-compose pull
docker-compose up --detach --force-recreate --remove-orphans



