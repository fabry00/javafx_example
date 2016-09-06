#!/bin/bash
docker build -t javafx_example_dev .
xhost +
docker run -it -v /tmp/.X11-unix:/tmp/.X11-unix -e DISPLAY=unix$DISPLAY javafx_example_dev
