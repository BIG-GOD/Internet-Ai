#!/usr/bin/python
# -*- coding: UTF-8 -*-

__author__ = 'zhkmx'
from time import sleep
##在此写入有关于火获取的函数
#def get_fire():
#   return


##接口函数，将火获取函数在此函数中调用
#@author zhkmxx930
##
def commit_fire(dict):
    while True:
        #get_fire():
        dict['fire'] = 3    #传入Manager临界资源值，该表达式右值应为fire获取有关函数所返回的实时火情*（此处的3为测试值）
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