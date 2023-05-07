#!/bin/bash
sudo yum update -y
#install npm
sudo yum install -y npm
#cd into the directory
cd /home/ec2-user/Front
#install node modules
sudo npm install
#start the server
sudo npm start
```