#!/usr/bin/python
# -*- coding: UTF-8 -*-
__author__ = 'zhkmx'

from multiprocessing import Pool, Manager
from time import sleep
import RPiHardware as RpiHardware
import RPiCommitServer as RpiServer



def main():
    RpiHardware.initialize()
    manager = Manager()  # Using Manager (Server process method) to manage a global dictionary
    dict = {
            'temp': 0,
            'humid': 0,
            'fire':0,
            'smoke':0,
            'gas':0,
            'pm':0
            }
    RpiServer.fetch_from_server(dict)
    print dict
    g_dict = manager.dict(dict)

    pool = Pool(processes=7)  # set the processes max number 7
    pool.apply_async(RpiHardware.commit_temprature, (g_dict,))
    pool.apply_async(RpiHardware.commit_fire, (g_dict,))
    pool.apply_async(RpiHardware.commit_gas, (g_dict,))
    pool.apply_async(RpiHardware.commit_pm, (g_dict,))
    #pool.apply_async(RpiHardware.commit_humidity, (g_dict,))
    pool.apply_async(RpiHardware.commit_smoke, (g_dict,))
    pool.apply_async(RpiServer.commit_to_server, (g_dict,))
    pool.close()
    pool.join()


if __name__ == "__main__":
    main()
