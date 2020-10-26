#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
# https://stackoverflow.com/questions/19622198/what-does-set-e-mean-in-a-bash-script
set -e

NOW=$(date +%Y-%m-%d' '%H:%M:%S.%N | cut -b 1-23)

########################## Functions Declaration ##########################
# Bash tips: Colors and formatting (ANSI/VT100 Control sequences)
# https://misc.flogisoft.com/bash/tip_colors_and_formatting
# Pass arguments into a function
# https://bash.cyberciti.biz/guide/Pass_arguments_into_a_function
function printInfo() {
  echo -e "$NOW \e[32m INFO --- $1\e[0m"
  return 0
}

function printWarn() {
  echo -e "$NOW \e[33m WARN --- $1\e[0m"
  return 0
}

function printError() {
  echo -e "$NOW \e[31mERROR --- $1\e[0m"
  return 0
}

########################## Functions Declaration ##########################
CURRENT_DIR=$(pwd)
printInfo "[INSTALL] CURRENT_DIR: $CURRENT_DIR"
command "ls"

# Run the Maven clean install
./mvnw clean install -Dmaven.javadoc.skip=true -Dgpg.skip=true --quiet --batch-mode --show-version

INSTALL_COMMAND_RESULT=$?

if [ "$INSTALL_COMMAND_RESULT" -eq 0 ]; then
  printInfo "[INSTALL] Installation succeed. INSTALL_COMMAND_RESULT: $INSTALL_COMMAND_RESULT"
else
  printError "[INSTALL] Installation failed. INSTALL_COMMAND_RESULT: $INSTALL_COMMAND_RESULT" >&2
  exit 1
fi
