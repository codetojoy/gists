import collections
import datetime

# this is a toy example and clearly not robust at all
Composer = collections.namedtuple("Composer", "name era id timestamp")
counter = 1
dict = {}

def get_timestamp():
    now = datetime.datetime.now()
    return now.strftime("%d/%m/%Y %H:%M:%S")

def get_composers():
    global dict
    return list(dict.values())

def get_composer_by_id(id):
    global dict
    return dict[id]

def new_composer(name, era):
    global counter
    global dict
    this_id = counter
    counter += 1
    composer = Composer(name=name, era=era, id=this_id, timestamp=get_timestamp())
    dict[this_id] = composer
    return composer

def update_composer(id, name, era):
    global dict
    composer = Composer(name=name, era=era, id=id, timestamp=get_timestamp())
    dict[id] = composer
    return composer

def delete_composer(id):
    global dict
    del dict[id]
