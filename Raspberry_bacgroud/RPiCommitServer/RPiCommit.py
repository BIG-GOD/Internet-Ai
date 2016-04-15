#!/usr/bin/python
# -*- coding: UTF-8 -*-
__author__ = 'zhkmx'
import MySQLdb
from time import sleep
import config as config
import GetMacAddr as mac

def getMac():
    return mac.get_mac_address()


def commit_to_server(dict):
    mac = getMac()
    db = MySQLdb.connect(host = config.host, port = config.port, user=config.user, passwd=config.passwd, db=config.db)
    db.autocommit(True)
    cursor = db.cursor()
    ISOTIMEFORMAT='%Y-%m-%d %X'


    while True:
        try:
            cursor.execute("UPDATE parameter SET temprature = %s, "
                           "humidity = %s, "
                           "gas = %s, "
                           "pm = %s, "
                           "fire = %s, "
                           "smoke = %s "
                           "update_time = %s"
                           "WHERE device_deviceId = '%s'"
                           % (str(dict['temp']), str(dict['humid']), str(dict['fire']), str(dict['smoke']), str(dict['gas']), str(dict['pm']),time.strftime(ISOTIMEFORMAT, time.localtime( time.time() ) ), str(getMac())))#str(getMac())
            print 'commited'
            sleep(1)
        except Exception,ex:
            print Exception,":",ex
            db.rollback()
