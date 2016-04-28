import RPi.GPIO as GPIO
import time

channel = 25

GPIO.setmode(GPIO.BCM)
time.sleep(1)

j = 100
while j != 0:
	GPIO.setup(channel, GPIO.IN)
	if GPIO.input(channel) == GPIO.HIGH:
		print "fire"
	time.sleep(1)
	if GPIO.input(channel) == GPIO.LOW:
		print "safe"
	time.sleep(1)
	j = j - 1

GPIO.cleanup()
