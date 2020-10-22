#!/bin/bash

# Run the maven install
./mvnw clean install -Dmaven.javadoc.skip=true -Dpgp.skip=true --quiet --batch-modeRun --show-version
