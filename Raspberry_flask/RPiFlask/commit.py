__author__ = 'zhkmx'
import commit
import time, datetime
import main as main
import config as config
import model


def commit_parameter_to_ecs(threadnName, delay):
    i = 0
    id = 1
    parameter_update = model.parameter(parameterId=id,
                                       temprature='%d' % (i + 9),
                                       humidity='%d' % (i),
                                       gas='%d' % (i + 3),
                                       pm='%d' % (i + 1),
                                       fire='%d' % ((i + 3) / 2 * 100),
                                       smoke='%d' % (i),
                                       device_deviceId='%d' % (40060114013633661549),
                                       update_time=datetime.datetime.now())
    i + 1
    config.db.session.add(parameter_update)
    config.db.session.commit()
    print 'comitted'


def commit_user_to_ecs(threadnName, delay):
    i = 0
    id = 1
    user_update = model.user(userId=id,
                                    userName='i + 9'
                                )
    i + 1
    config.db.session.add(user_update)
    config.db.session.commit()
    print 'comitted'


class commit:
    def thread_commit(self):
        try:
            commit.start_new_thread(commit_to_ecs, ('Th1', 3))
            # thread.start_new_thread(commit_to_ecs, ('Th2', 4))
        except:
            print 'error'

        while 1:
            pass