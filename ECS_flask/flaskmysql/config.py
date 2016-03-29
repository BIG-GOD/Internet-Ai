__author__ = 'zhkmx'
import flaskmysql
from flask.ext.sqlalchemy import SQLAlchemy
from sqlalchemy import engine, create_engine
from sqlalchemy.orm import sessionmaker


db = SQLAlchemy(flaskmysql.app)
engine = create_engine('mysql://root:5407922@localhost/andriodpi')
Session = sessionmaker(bind=engine)
session = Session()

# def config(flaskmysql.app):
#     app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:5407922@localhost/andriodpi'
#     app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True