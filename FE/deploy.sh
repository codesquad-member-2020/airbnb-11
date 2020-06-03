#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/fedist
cd $REPOSITORY

echo "> FE 배포"
sudo cp -rf dist/* /var/www/html/
