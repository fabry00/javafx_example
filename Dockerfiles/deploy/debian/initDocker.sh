#!/bin/bash
docker build -t javafx_example .
xhost +
docker run -v /tmp/.X11-unix:/tmp/.X11-unix -e DISPLAY=unix$DISPLAY javafx_example
