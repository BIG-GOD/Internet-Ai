FROM ubuntu:14.04
MAINTAINER Zhao "zhkmxx930@163.com"

RUN apt-get update 
RUN apt-get -qqy install mysql-client
RUN apt-get -qqy install python2.7-dev
RUN apt-get -qqy install -y python-setuptools 
RUN apt-get -qqy install libmysqld-dev
RUN apt-get -qqy install vim
RUN easy_install pip 
ADD . /app 
WORKDIR /app
RUN pip install -r requirements.txt

EXPOSE 5000

CMD ['python ','/app/flaskmysql.py']