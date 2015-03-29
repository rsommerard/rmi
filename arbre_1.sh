#!/bin/bash

cd bin/

# Clean process
kill `ps -ef | grep rmiregistry | awk '{print $2}'` > /dev/null
kill `ps -ef | grep "java fr.rsommerard" | awk '{print $2}'` > /dev/null

# Compile RMI implementation
rmic fr.rsommerard.rmi.MonitorImpl
rmic fr.rsommerard.rmi.SiteImpl

# lance rmiregistry
rmiregistry &

# initialise le moniteur dans un nouveau terminal
gnome-terminal -e "java fr.rsommerard.initializer.MonitorInitializer"
sleep 0.5

# initialise les sites
for i in {1..6}
do
  java fr.rsommerard.initializer.SiteInitializer $i &
  sleep 0.2
done

# configuration en arbre
java fr.rsommerard.initializer.ConnectionInitializer 1 2
sleep 0.2
java fr.rsommerard.initializer.ConnectionInitializer 1 5
sleep 0.2
java fr.rsommerard.initializer.ConnectionInitializer 2 3
sleep 0.2
java fr.rsommerard.initializer.ConnectionInitializer 2 4
sleep 0.2
java fr.rsommerard.initializer.ConnectionInitializer 5 6
sleep 0.2

java fr.rsommerard.initializer.MessageInitializer 1 "This is a test !"
