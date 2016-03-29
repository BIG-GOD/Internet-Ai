from flask import Flask, request, render_template, jsonify, json, session
import config, model
import config as main
from flask.ext.sqlalchemy import SQLAlchemy
from sqlalchemy import engine, create_engine
from sqlalchemy.orm import sessionmaker
import time, datetime


app = Flask(__name__)

db = SQLAlchemy(app)
engine = create_engine('mysql://root:5407922@120.27.44.239/andriodpi')
Session = sessionmaker(bind=engine)
session = Session()


class user(db.Model):
    __tablename__ = 'user'
    userId = db.Column(db.Integer, primary_key=True)
    userPasswd = db.Column(db.String(45))
    userName = db.Column(db.String(45))
    userMobile = db.Column(db.String(13))
    userEmail = db.Column(db.String(50))
    userIspaid = db.Column(db.Integer)
    userRegtime = db.Column(db.Date)


class device(db.Model):
    __tablename__ = 'device'
    deviceId = db.Column(db.String(20), primary_key=True)
    deviceName = db.Column(db.String(45))
    deviceIp = db.Column(db.String(45))
    devicePort = db.Column(db.String(45))
    deviceProductionDate = db.Column(db.Date)
    deviceStaus = db.Column(db.String(45))
    user_userId = db.Column(db.Integer)


class parameter(db.Model):
    __tablename__ = 'parameter'
    parameterId = db.Column(db.Integer, primary_key=True)
    temprature = db.Column(db.String(45))
    humidity = db.Column(db.String(45))
    gas = db.Column(db.String(45))
    pm = db.Column(db.String(45))
    fire = db.Column(db.String(45))
    smoke = db.Column(db.String(45))
    device_deviceId = db.Column(db.String(20))
    update_time = db.Column(db.DateTime)


def commit_parameter_to_ecs(deviceId, tempara, humidity, gas, pm, fire, smoke, update_time):
    parameter_update = parameter(
        parameterId=deviceId,
        temprature=tempara,
        humidity=humidity,
        gas=gas,
        pm=pm,
        fire=fire,
        smoke=smoke,
        update_time=update_time
    )
    session.merge(parameter_update)
    session.commit()


def commit_user_to_ecs():
    user_update = user(userId=1, userName=time.time())
    session.merge(user_update)
    session.commit()


@app.route('/update/user', methods=['GET', 'POST'])
def hello_world():
    while 1:
        commit_user_to_ecs()
        time.sleep(3)
    return 'updated' + time.time()


@app.route('/update/parameter', methods=['GET', 'POST'])
def update_parameter():
    i = 0
    while 1:
        commit_parameter_to_ecs(1, i, i * 10 / 2, i + 9, i * 100, i + 99, i + 1, datetime.datetime.now())
        i += 2
        time.sleep(3)
    return i + ' ' + datetime.datetime()


if __name__ == '__main__':
    app.run(port=10033, debug='true')
