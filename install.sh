#!/bin/bash

# Run the maven install
./mvnw clean install -Dmaven.javadoc.skip=true -Dgpg.skip=true --batch-mode --show-version
