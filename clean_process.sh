#!/bin/bash

# Clean process
kill `ps -ef | grep rmiregistry | awk '{print $2}'` > /dev/null 2>&1
kill `ps -ef | grep "java fr.rsommerard" | awk '{print $2}'` > /dev/null 2>&1
