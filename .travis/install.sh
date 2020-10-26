#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
# https://stackoverflow.com/questions/19622198/what-does-set-e-mean-in-a-bash-script
set -e

CURRENT_DIR=$(pwd)
echo "[INSTALL] INFO CURRENT_DIR: $CURRENT_DIR"
command "ll"

INSTALL_COMMAND_RESULT=$(./mvnw clean install -Dmaven.javadoc.skip=true -Dgpg.skip=true --quiet --batch-mode --show-version)

if [ "$INSTALL_COMMAND_RESULT" -eq 0 ]; then
  echo "[INSTALL] INFO Installation succeed. INSTALL_COMMAND_RESULT: $INSTALL_COMMAND_RESULT"
else
  echo "[INSTALL] INFO Installation failed. INSTALL_COMMAND_RESULT: $INSTALL_COMMAND_RESULT" >&2
  exit 1
fi
