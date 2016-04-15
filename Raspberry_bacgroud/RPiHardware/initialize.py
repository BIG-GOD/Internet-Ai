#!/usr/bin/python
# -*- coding: UTF-8 -*-
__author__ = 'YaoEmily'

import RPi.GPIO as GPIO
import time

def initialize():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(23, GPIO.IN)
    GPIO.setup(24, GPIO.IN)
    return
