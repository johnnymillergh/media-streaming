#!/bin/bash
# expects variables to be set:
# - OSSRH_USERNAME
# - OSSRH_PASSWORD
# - GPG_KEY_NAME
# - GPG_PASSPHRASE
# expects file to exist:
# - .travis/gpg.asc

# Exit immediately if a command exits with a non-zero status.
# https://stackoverflow.com/questions/19622198/what-does-set-e-mean-in-a-bash-script
set -e

########################## Functions Declaration ##########################
# Bash tips: Colors and formatting (ANSI/VT100 Control sequences)
# https://misc.flogisoft.com/bash/tip_colors_and_formatting
# Pass arguments into a function
# https://bash.cyberciti.biz/guide/Pass_arguments_into_a_function
function now() {
  nowVariable=$(date +%Y-%m-%d' '%H:%M:%S.%N | cut -b 1-23)
  echo "$nowVariable"
}

function printInfo() {
  echo -e "$(now) \e[32m INFO --- $1\e[0m"
  return 0
}

function printWarn() {
  echo -e "$(now) \e[33m WARN --- $1\e[0m"
  return 0
}

function printError() {
  echo -e "$(now) \e[31mERROR --- $1\e[0m"
  return 0
}

# Check the variables are set
if [ -z "$OSSRH_USERNAME" ]; then
  printError "[DEPLOY] Missing environment value: OSSRH_USERNAME" >&2
  exit 1
fi

if [ -z "$OSSRH_PASSWORD" ]; then
  printError "[DEPLOY] Missing environment value: OSSRH_PASSWORD" >&2
  exit 1
fi

if [ -z "$GPG_KEY_NAME" ]; then
  printError "[DEPLOY] Missing environment value: GPG_KEY_NAME" >&2
  exit 1
fi

if [ -z "$GPG_PASSPHRASE" ]; then
  printError "[DEPLOY] Missing environment value: GPG_PASSPHRASE" >&2
  exit 1
fi

printInfo "[DEPLOY] All expected variables are set. OSSRH_USERNAME, OSSRH_PASSWORD, GPG_KEY_NAME and GPG_PASSPHRASE"

# If decrypted file .travis/gpg.asc not exists
if [ ! -f "${TRAVIS_BUILD_DIR}/.travis/gpg.asc" ]; then
  printError "[DEPLOY] Missing decrypted file: .travis/gpg.asc" >&2
  exit 1
else
  printInfo "[DEPLOY] Found decrypted file: .travis/gpg.asc"
fi

# Prepare the local keyring (requires travis to have decrypted the file beforehand)
gpg --fast-import .travis/gpg.asc

# If `TRAVIS_TAG` string is not empty
if [ -n "$TRAVIS_TAG" ]; then
  printWarn "[DEPLOY] Maven deploy on a tag -> set pom.xml <version> to TRAVIS_TAG: $TRAVIS_TAG"
  mvn --settings "${TRAVIS_BUILD_DIR}/.travis/maven-settings.xml" org.codehaus.mojo:versions-maven-plugin:2.7:set -DnewVersion=$TRAVIS_TAG 1>/dev/null 2>/dev/null
else
  printWarn "[DEPLOY] Maven deploy not on a tag -> keep snapshot version in pom.xml"
fi

# Run the maven deploy steps
mvn deploy -P publish -DskipTests=true --quiet --settings "${TRAVIS_BUILD_DIR}/.travis/maven-settings.xml"

DEPLOY_COMMAND_RESULT=$?

if [ "$DEPLOY_COMMAND_RESULT" -eq 0 ]; then
  printInfo "[DEPLOY] Deployment succeed. DEPLOY_COMMAND_RESULT: $DEPLOY_COMMAND_RESULT"
else
  printError "[DEPLOY] Deployment failed. DEPLOY_COMMAND_RESULT: $DEPLOY_COMMAND_RESULT" >&2
  exit 1
fi
