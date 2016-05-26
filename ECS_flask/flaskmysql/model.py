__author__ = 'eason'
import config as main
from werkzeug.security import generate_password_hash, check_password_hash
db = main.db


class user(db.Model):
    __tablename__ = 'user'
    userId = db.Column(db.Integer, primary_key=True)
    userPasswd = db.Column(db.String(45))
    userName = db.Column(db.String(45))
    userMobile = db.Column(db.String(13))
    password_hash=db.Column(db.String(128))
    userEmail = db.Column(db.String(50))
    userIspaid = db.Column(db.Integer)
    userAddr = db.Column(db.String(100))
    userTel = db.Column(db.String(20))
    userA = db.Column(db.String(100))
    
    @property
    def userPasswd(self):
        print '123'
        raise AttributeError('you cant read it')
    @userPasswd.setter
    def userPasswd(self,userPasswd):
        self.password_hash=generate_password_hash(userPasswd)

    def check_password_hash(self,userPasswd):
        return check_password_hash(self.password_hash,userPasswd)


class device(db.Model):
    __tablename__ = 'device'
    deviceId = db.Column(db.String(20),primary_key=True)
    deviceName = db.Column(db.String(45))
    deviceIp = db.Column(db.String(45))
    devicePort = db.Column(db.String(45))
    deviceProductionDate = db.Column(db.Date)
    deviceStaus = db.Column(db.String(45))
    user_userId = db.Column(db.Integer)

class parameter(db.Model):
    __tablename__ = 'parameter'
    parameterId = db.Column(db.Integer,primary_key=True)
    temprature = db.Column(db.String(45))
    humidity = db.Column(db.String(45))
    gas = db.Column(db.String(45))
    pm = db.Column(db.String(45))
    fire = db.Column(db.String(45))
    smoke = db.Column(db.String(45))
    device_deviceId = db.Column(db.String(20))
    update_time = db.Column(db.DateTime)
    
#history parameter
class history(db.Model):
    __tablename__ = 'history'
    historyid = db.Column(db.Integer,primary_key=True)
    temprature = db.Column(db.String(45))
    humidity = db.Column(db.String(45))
    gas = db.Column(db.String(45))
    fire = db.Column(db.String(45))
    device_deviceId = db.Column(db.String(20))
    updatetime = db.Column(db.DateTime)
