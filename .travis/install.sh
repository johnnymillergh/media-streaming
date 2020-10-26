#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
# https://stackoverflow.com/questions/19622198/what-does-set-e-mean-in-a-bash-script
set -e

function now() {
  echo date +%Y-%m-%d' '%H:%M:%S.%N | cut -b 1-23
  return 0
}

CURRENT_DIR=$(pwd)
echo "$(now)  INFO --- [INSTALL] CURRENT_DIR: $CURRENT_DIR"
command "ls"

# Run the Maven clean install
./mvnw clean install -Dmaven.javadoc.skip=true -Dgpg.skip=true --quiet --batch-mode --show-version

INSTALL_COMMAND_RESULT=$?

if [ "$INSTALL_COMMAND_RESULT" -eq 0 ]; then
  echo "$(now)  INFO --- [INSTALL] Installation succeed. INSTALL_COMMAND_RESULT: $INSTALL_COMMAND_RESULT"
else
  echo "$(now)  INFO --- [INSTALL] Installation failed. INSTALL_COMMAND_RESULT: $INSTALL_COMMAND_RESULT" >&2
  exit 1
fi
