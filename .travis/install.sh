#!/bin/bash

CURRENT_DIR=$(pwd)

echo "[INSTALL] INFO CURRENT_DIR: $CURRENT_DIR"

# Run the maven install
./mvnw clean install -Dmaven.javadoc.skip=true -Dgpg.skip=true --quiet --batch-mode --show-version
