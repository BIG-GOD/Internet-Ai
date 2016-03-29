from flask import Flask, request, render_template, jsonify, json, session
from sqlalchemy import text
import model
import config as config

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


# Return user info
@app.route('/user/<user_id>', methods=['GET', 'POST'])
def user(user_id):
    result = model.user.query.filter_by(userId=user_id).first()
    if result is None:
        json_result = {'user_id': None}
        return json.dumps(json_result, ensure_ascii=False)
    else:
        json_result = {'user_id': result.userId,
                       'user_Name': result.userName,
                       'user_Email': result.userEmail,
                       'user_Mobile': result.userMobile,
                       'user_Passwd': result.userPasswd,
                       'user_Ispaid': result.userIspaid}
        return json.dumps(json_result, ensure_ascii=False)


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
