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

NOW=$(date +%Y-%m-%d' '%H:%M:%S.%N | cut -b 1-23)

# Check the variables are set
if [ -z "$OSSRH_USERNAME" ]; then
  echo "$NOW ERROR --- [DEPLOY] Missing environment value: OSSRH_USERNAME" >&2
  exit 1
fi

if [ -z "$OSSRH_PASSWORD" ]; then
  echo "$NOW ERROR --- [DEPLOY] Missing environment value: OSSRH_PASSWORD" >&2
  exit 1
fi

if [ -z "$GPG_KEY_NAME" ]; then
  echo "$NOW ERROR --- [DEPLOY] Missing environment value: GPG_KEY_NAME" >&2
  exit 1
fi

if [ -z "$GPG_PASSPHRASE" ]; then
  echo "$NOW ERROR --- [DEPLOY] Missing environment value: GPG_PASSPHRASE" >&2
  exit 1
fi

echo "$NOW INFO --- [DEPLOY] All expected variables are set. OSSRH_USERNAME, OSSRH_PASSWORD, GPG_KEY_NAME and GPG_PASSPHRASE" >&2

# If decrypted file .travis/gpg.asc not exists
if [ ! -f "${TRAVIS_BUILD_DIR}/.travis/gpg.asc" ]; then
  echo "$NOW ERROR --- [DEPLOY] Missing decrypted file: .travis/gpg.asc" >&2
  exit 1
else
  echo "$NOW INFO --- [DEPLOY] Found decrypted file: .travis/gpg.asc"
fi

# Prepare the local keyring (requires travis to have decrypted the file beforehand)
gpg --fast-import .travis/gpg.asc

# If `TRAVIS_TAG` string is not empty
if [ -n "$TRAVIS_TAG" ]; then
  echo "$NOW  WARN --- [DEPLOY] Maven deploy on a tag -> set pom.xml <version> to TRAVIS_TAG: $TRAVIS_TAG"
  mvn --settings "${TRAVIS_BUILD_DIR}/.travis/maven-settings.xml" org.codehaus.mojo:versions-maven-plugin:2.7:set -DnewVersion=$TRAVIS_TAG 1>/dev/null 2>/dev/null
else
  echo "$NOW  WARN --- [DEPLOY] Maven deploy not on a tag -> keep snapshot version in pom.xml"
fi

# Run the maven deploy steps
mvn deploy -P publish -DskipTests=true --quiet --settings "${TRAVIS_BUILD_DIR}/.travis/maven-settings.xml"

DEPLOY_COMMAND_RESULT=$?

if [ "$DEPLOY_COMMAND_RESULT" -eq 0 ]; then
  echo "$NOW  INFO --- [DEPLOY] Deployment succeed. DEPLOY_COMMAND_RESULT: $DEPLOY_COMMAND_RESULT"
else
  echo "$NOW ERROR --- [DEPLOY] Deployment failed. DEPLOY_COMMAND_RESULT: $DEPLOY_COMMAND_RESULT" >&2
  exit 1
fi
