#!/usr/bin/python
# -*- coding: UTF-8 -*-
__author__ = 'zhkmx'
from time import sleep
##在此写入有关于pm值获取的函数
#def get_pm():
#   return


##接口函数，将pm值获取函数在此函数中调用
#@author zhkmxx930
##
def commit_pm(dict):
    while True:
        #get_pm()
        dict['pm'] = 5    #传入Manager临界资源值，该表达式右值应为PM获取有关函数所返回的实时PM*（此处的5为测试值）
        print 'updated5'
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