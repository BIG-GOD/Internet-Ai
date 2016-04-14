#!/usr/bin/python
# -*- coding: UTF-8 -*-
__author__ = 'zhkmx'
import MySQLdb
import config as config
import GetMacAddr as mac


def getMac():
    return mac.get_mac_address()


def fetch_from_server(dict):
    mac = getMac()
    db = MySQLdb.connect(host=config.host, port=config.port, user=config.user, passwd=config.passwd, db=config.db)
    db.autocommit(True)
    cursor = db.cursor()
    try:
        cursor.execute("SELECT * FROM parameter WHERE device_deviceId = '%s'" % (str(getMac())))  # str(getMac())
        result = cursor.fetchall()
        for row in result:
            dict['temp'] = row[1]
            dict['humid'] = row[2]
            dict['gas'] = row[3]
            dict['pm'] = row[4]
            dict['fire'] = row[5]
            dict['smoke'] = row[6]
        print 'fetched'
    except Exception, ex:
        print Exception, ":", ex
        db.rollback()



        # dict = {
        # 'temp': 0,
        #            'humid': 0,
        #            'fire':0,
        #            'smoke':0,
        #            'gas':0,
        #            'pm':0
        #            }