# __author__ = 'zhkmx'
# import config as main
# from flask.ext.sqlalchemy import SQLAlchemy
# # import main
# # from main import app
# from sqlalchemy import engine, create_engine
# from sqlalchemy.orm import sessionmaker
#
#
# db = SQLAlchemy(app)
#
#
# class user(db.Model):
#     __tablename__ = 'user'
#     userId = db.Column(db.Integer, primary_key=True)
#     userPasswd = db.Column(db.String(45))
#     userName = db.Column(db.String(45))
#     userMobile = db.Column(db.String(13))
#     userEmail = db.Column(db.String(50))
#     userIspaid = db.Column(db.Integer)
#
# class device(db.Model):
#     __tablename__ = 'device'
#     deviceId = db.Column(db.String(20),primary_key=True)
#     deviceName = db.Column(db.String(45))
#     deviceIp = db.Column(db.String(45))
#     devicePort = db.Column(db.String(45))
#     deviceProductionDate = db.Column(db.Date)
#     deviceStaus = db.Column(db.String(45))
#     user_userId = db.Column(db.Integer)
#
# class parameter(db.Model):
#     __tablename__ = 'parameter'
#     parameterId = db.Column(db.Integer,primary_key=True)
#     temprature = db.Column(db.String(45))
#     humidity = db.Column(db.String(45))
#     gas = db.Column(db.String(45))
#     pm = db.Column(db.String(45))
#     fire = db.Column(db.String(45))
#     smoke = db.Column(db.String(45))
#     device_deviceId = db.Column(db.String(20))
#     update_time = db.Column(db.DateTime)
