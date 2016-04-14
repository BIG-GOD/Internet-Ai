#!/usr/bin/python
# -*- coding: UTF-8 -*-
__author__ = 'zhkmx'
import uuid
import socket
#import fcntl
import struct
# return mac address
def get_mac_address():
    mac = uuid.UUID(int=uuid.getnode()).hex[-12:]
    return ":".join([mac[e:e + 2] for e in range(0, 11, 2)])


#output hostname and ip_address
def get_ip_address():
    myname = socket.getfqdn(socket.gethostname())

    myaddr = socket.gethostbyname(myname)
    return ' myname:%s  myaddr:%s' % (myname, myaddr)

print get_mac_address()
#input hostname ouput ip_address
# def get_ip_address(ifname):
#     s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
#     return socket.inet_ntoa(fcntl.ioctl(
#         s.fileno(),
#         0x8915,  # SIOCGIFADDR
#         struct.pack('256s', ifname[:15])
#     )[20:24])
