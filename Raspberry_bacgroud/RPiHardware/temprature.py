#!/usr/bin/python
# -*- coding: UTF-8 -*-
__author__ = 'zhkmx'
from time import sleep

##在此写入有关于温度获取的函数
def get_humidity_temprature(th):
    channel = 18
    data = []
    j = 0
    
    GPIO.setup(channel, GPIO.OUT)
    GPIO.output(channel, GPIO.LOW)
    time.sleep(0.02)
    GPIO.output(channel, GPIO.HIGH)
    GPIO.setup(channel, GPIO.IN)
    
    while GPIO.input(channel) == GPIO.LOW:
        continue
    while GPIO.input(channel) == GPIO.HIGH:
	continue
    while j < 40:
	k = 0
	while GPIO.input(channel) == GPIO.LOW:
	    continue
	while GPIO.input(channel) == GPIO.HIGH:
            k += 1
	    if k > 100:
		break
	if k < 8:
	    data.append(0)
	else:
	    data.append(1)

	j += 1

    humidity_bit = data[0:8]
    humidity_point_bit = data[8:16]
    temperature_bit = data[16:24]
    temperature_point_bit = data[24:32]
    check_bit = data[32:40]

    humidity = 0
    humidity_point = 0
    temperature = 0
    temperature_point = 0
    check = 0

    for i in range(8):
	humidity += humidity_bit[i] * 2 ** (7-i)
	humidity_point += humidity_point_bit[i] * 2 ** (7-i)
	temperature += temperature_bit[i] * 2 ** (7-i)
	temperature_point += temperature_point_bit[i] * 2 ** (7-i)
	check += check_bit[i] * 2 ** (7-i)

    tmp = humidity + humidity_point + temperature + temperature_point

    if check == tmp:
        if th == 't':
            return temperature
        else:
            return humidity
    else:
        return -1000


##接口函数，将温度获取函数在此函数中调用
#@author zhkmxx930
##
def commit_temprature(dict):
    i = 0
    while True:
        temprature = get_humidity_temprature('t')
        humidity = get_humidity_temprature('h')
        if (temprature == -1000 or humidity == -1000):
            i = i + 1
        else:
            dict['temp'] = temprature    #传入Manager临界资源值，该表达式右值应为温度获取有关函数所返回的实时温度*（此处的1为测试值）
            dict['humid'] = humidity
            i = 0
        if i == 50:
            i = 0
            dict['temp'] = -1000
            dict['humid'] = -1000
        print 'updated1'
        sleep(1)            #同步标注时间1s
    return



 # dict = {
 #            'temp': 0,
 #            'humid': 0,
 #            'fire':0,
 #            'smoke':0,
 #            'gas':0,
 #            'pm':0
 #            }
