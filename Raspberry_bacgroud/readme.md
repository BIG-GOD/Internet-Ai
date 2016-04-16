# 树莓派传感器监控信息后台

## 文件夹概述

* RPiCommitServer 向ECS服务器提交监控数据，以及树莓派上电后的与数据库同步功能代码
* RPiHardware 通过RPi.GPIO 实现的各传感器信息监控功能

## 主文件概述

* RPiMultiProc.py 通过多进程池实现的各传感器获取数据，与向服务器提交数据的功能