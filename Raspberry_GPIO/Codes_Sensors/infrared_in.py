import RPi.GPIO as GPIO
import time

channel = 24

GPIO.setmode(GPIO.BCM)
time.sleep(1)

GPIO.setup(channel, GPIO.IN)

circle = 10
i = 100
j = 0
nopeople=0
yespeople=0
while i != 0:
	while j < circle:
        	if GPIO.input(channel) == GPIO.LOW:
               		nopeople = nopeople + 1
                        time.sleep(0.1)
       		else if GPIO.input(channel) == GPIO.HIGH:
                	yespeople = yespeople + 1
                        time.sleep(0.1)
		j = j + 1
	j = 0
	if(yespeople > 5):
		print "There is someone coming"
	else:
		print "Nobody is here"
        i = i - 1
	nopeople = 0
	yespeople = 0


GPIO.cleanup()
