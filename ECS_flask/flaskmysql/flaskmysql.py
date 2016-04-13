from flask import Flask, request, render_template, jsonify, json, session
from sqlalchemy import text
import model
import config as config

app = Flask(__name__)
app.config['SECRET_KEY'] = 'Q\xc5h\x93\xf4s\x87\xe1\xdf\x0c6r\xb8q\x07\xd6-\xc5\xb8\x1ew1\x8c\xfa'

@app.route('/')
def hello_world():
    return 'Hello World!'

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
        return 'succeed'


@app.route('/user/register/<user_username>&<user_userpasswd>&<user_moblie>&<user_tel>&<user_add>&<user_ispaid>\
            &<user_regtime>',methods=['GET','POST'])
def register(user_username,user_userpasswd,user_mobile,user_tel,user_add,user_ispaid,user_regtime):
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

@app.route('/user/login/<user_username>&<user_userpassword>',methods=['GET','POST'])
def login(user_username,user_userpassword):
    result=config.session.query(model.user).\
        from_statement(text("select * from user where userName=:username")).\
        params(username=user_username).first()
    if result is None:
        print 'the username is not exist!'
    else:
    #print type(result)
    #print '111111111'
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


if __name__ == '__main__':
    app.run(debug='true')
