import time
import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)

IN1 = 25
IN2 = 12
pwm = 16

GPIO.setup(IN1, GPIO.OUT)
GPIO.setup(IN2, GPIO.OUT)
GPIO.setup(pwm, GPIO.OUT)
dc = 60
p = GPIO.PWM(pwm, dc)

def fan_start():
	p.start(0)
	GPIO.output(IN1, GPIO.HIGH)
	GPIO.output(IN2, GPIO.LOW)

def fan_stop():
	GPIO.output(IN1, GPIO.LOW)
	GPIO.output(IN2, GPIO.LOW)

def fan_plus():
	dc = dc + 20
	if dc > 100:
		dc = 100
	p.ChangeDutyCycle(dc)

def fan_minus():
	dc = dc - 20
	if dc < 0:
		dc = 0
	p.ChangeDutyCycle(dc)
