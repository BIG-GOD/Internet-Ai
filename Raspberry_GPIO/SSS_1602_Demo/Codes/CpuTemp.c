#include<stdio.h>
#include<stdlib.h>
#include<wiringPi.h>
#include<lcd.h>

int main(int argc, char const *argv[])
{
  /* code */
  int i = 0,display = 0,temprature = 0;
  FILE *fp;
  wiringPiSetup();
  for(i = 0;i < 8;i++)
  {
    pinMode(i,OUTPUT);
  }
  display = lcdInit(2,16,8,11,10,0,1,2,3,4,5,6,7,);
  lcdHome(display);
  lcdClear(display);
  lcdPosition(display,0,0);
  if((fp = fopen("/sys/class/thermal/thermal_zone0/temp","r"))=NULL)
  {
    lcdPutchar(display,"FAILED");
    exit(0);
  }0
  while(1)
  {
    fscanf(fp,"%d",&temprature);
    lcdPrintf(display,"Temp:%f℃",temprature/1000);
    delay(800);
  }
  return 0;
}
