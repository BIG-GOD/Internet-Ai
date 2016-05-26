from flask import Flask, request, render_template, jsonify, json, session
from sqlalchemy import text
import model
import config as config

import socket
import fcntl
import struct

app = Flask(__name__)
app.config['SECRET_KEY'] = 'Q\xc5h\x93\xf4s\x87\xe1\xdf\x0c6r\xb8q\x07\xd6-\xc5\xb8\x1ew1\x8c\xfa'


#This function is used to get the ip from host
def get_ip_address(ifname):
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    return socket.inet_ntoa(fcntl.ioctl(
        s.fileno(),
        0x8915,  # SIOCGIFADDR
        struct.pack('256s', ifname[:15])
    )[20:24])
#ipaddr is the value of the eth0 ip address
ipaddr =   get_ip_address('eth0')

@app.route('/')
def hello_world():
    return 'Hello World!'
    
#update user information
@app.route('/user/update/<userMobile>&<userEmail>&<userName>&<userTel>&<userAddr>',methods=['POST','GET'])
def update_info(userMobile,userEmail,userName,userTel,userAddr):
    user = config.session.query(model.user).filter(model.user.userName == userName).first()
    userId = user.userId
    # userId = request.form["userId"]
    # userMobile = request.form["userMobile"]
    # userEmail = request.form["userEmail"]
    # userName = request.form["userName"]
    # userAddrcode = userAddr.decode(CODE)CODE
    config.session.query(model.user).filter(model.user.userId == userId).update({
        model.user.userEmail:userEmail,
        model.user.userMobile:userMobile,
        model.user.userAddr:generate_password_hash(userAddr),
        model.user.userTel:userTel
    })
    config.session.commit()
    return 'updated'

@app.route('/user/changepsw/<userName>&<userPsw>', methods=['POST', 'GET'])
def change_psw(userName,userPsw):
    user = config.session.query(model.user).filter(model.user.userName == userName).first()
    userId = user.userId
    config.session.query(model.user).filter(model.user.userId == userId).update({
        model.user.password_hash:generate_password_hash(userPsw)
    })
    config.session.commit()
    return 'updated'

    
#Register router post
@app.route('/user/register',methods=['POST','GET'])
def reg():
    user_name=request.form["username"]
    user_password=request.form["user_password"]
    result=config.session.query(model.user).\
       from_statement(text("SELECT * from user where userName=:username")).\
        params(username=user_name).first()
    if (result is None):
        new_user = model.user(userName=user_name,userPasswd=user_password)
        config.session.merge(new_user)
        config.session.commit()
        config.db.session.close()
        return '<html>succeed</html>'
    return 'username is already exist!'

#Register router get
@app.route('/user/register/<user_username>&<user_userpasswd>',methods=['GET','POST'])
def register(user_username,user_userpasswd):
    result=config.session.query(model.user).\
       from_statement(text("SELECT * from user where userName=:username")).\
        params(username=user_username).first()
    if (result is None):
        new_user = model.user(userName=user_username,userPasswd=user_userpasswd)
        config.session.merge(new_user)
        config.session.commit()
        config.db.session.close()
        return 'succeed'
    return 'username is already exist!'

#Login router
@app.route('/user/login/<user_username>&<user_userpassword>',methods=['GET','POST'])
def login(user_username,user_userpassword):
    result=config.session.query(model.user).\
        from_statement(text("select * from user where userName=:username")).\
        params(username=user_username).first()
    if result is None:
        print 'the username is not exist!'
    else:
        if result.check_password_hash(user_userpassword):
            return 'succeed login'
        else:
            return 'error username or password'

#Return the state of the sensor
@app.route('/state/<device_id>&<user_id>', methods=['GET'])
def state(user_id, device_id):
    result = config.session.query(model.parameter).\
        from_statement(text("SELECT * from parameter where device_deviceId=:deviceid")).\
        params(deviceid=device_id).first()
    if result is None:
        json_result = {'parameterId': None}
        return json.dumps(json_result, ensure_ascii=False)
    else:
        json_result = {'parameterId': result.parameterId,
                       'temprature': result.temprature,
                       'humidity': result.humidity,
                       'gas': result.gas,
                       'pm': result.pm,
                       'smoke': result.smoke,
                       'updatetime':result.update_time}
        return json.dumps(json_result, ensure_ascii=False)
        
#Get history data 3 days before today.
@app.route('/state/history',methods=['POST'])
def historypost():
    user_id=request.form["user_id"]
    device_deviceId=request.form["device_deviceId"]
    results=config.session.query(model.history).\
        from_statement(text("select * from history where device_deviceId=:deviceid ORDER BY updatetime DESC  LIMIT 36")).\
        params(deviceid=device_deviceId).all()
    if results is None:
        return jsonify({"history_parameter":None})
    else:
        list_result=[]
        dict_result={}
        for i in range(len(results)):
            list_result.append((results[i].updatetime.strftime("%Y-%m-%d %H:%M:%S"),results[i].temprature,results[i].humidity,results[i].gas,results[i].fire))
        dict_result=map(lambda x: dict(zip(('updatetime', 'temprature', 'humidity','gas','fire'), x)), list_result)
        return json.dumps(dict_result)

#The value of the host is captured from eth0 ip address
if __name__ == '__main__':
    app.run(host=ipaddr,debug='true')
