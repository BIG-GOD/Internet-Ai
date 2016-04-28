#!/usr/bin/python

import RPi.GPIO as GPIO
import time

channel = 23

GPIO.setmode(GPIO.BCM)
time.sleep(1)
j = 20

while j != 0:
	GPIO.setup(channel, GPIO.OUT)
	GPIO.output(channel, GPIO.LOW)
	print "low"
	time.sleep(1)
	GPIO.output(channel, GPIO.HIGH)
	print "high"
	time.sleep(1)
	j = j - 1
GPIO.output(channel, GPIO.LOW)
GPIO.cleanup()
