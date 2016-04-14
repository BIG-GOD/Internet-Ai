#!/usr/bin/python
# -*- coding: UTF-8 -*-

__author__ = 'zhkmx'
from time import sleep

##火焰获取函数
#@author YaoEmily
##
def get_fire():
    channel = 23
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(channel, GPIO.IN)
    if GPIO.input(channel) == GPIO.HIGH:
	return 1
    else:
	return 0


##接口函数，将火获取函数在此函数中调用
#@author zhkmxx930
##
def commit_fire(dict):
    while True:
        fire = get_fire()
        dict['fire'] = fire    #传入Manager临界资源值，该表达式右值应为fire获取有关函数所返回的实时火情*（此处的3为测试值）
        print 'updated3'
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
