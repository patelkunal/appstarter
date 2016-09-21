#!/usr/bin/env bash

MSG="hello_world"
ARG_VERSION=4.42

java -server \
     -classpath "lib/*" \
	 -Dmessage=${MSG-hello_there} \
	 -Dextra.args=-Dapp.extra.args=${ARG_VERSION} \
     org.coderearth.kitchens.appstarter.Application

