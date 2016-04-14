#!/usr/bin/python
# -*- coding: UTF-8 -*-
__author__ = 'zhkmx'
from time import sleep
##在此写入有关于湿度获取的函数
#def get_humidity():
#   return


##接口函数，将湿度获取函数在此函数中调用
#@author zhkmxx930
##
def commit_humidity(dict):
    while True:
        #get_humidity()
        dict['humid'] = 2    #传入Manager临界资源值，该表达式右值应为humidity获取有关函数所返回的实时湿度*（此处的2为测试值）
        print 'updated2'
        sleep(1)             #同步标注时间1s
    return



 # dict = {
 #            'temp': 0,
 #            'humid': 0,
 #            'fire':0,
 #            'smoke':0,
 #            'gas':0,
 #            'pm':0
 #            }