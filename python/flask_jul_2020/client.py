
import datetime
import requests
import sys

# ----------------------------------
# REST api calls

PING_ENDPOINT = "http://0.0.0.0:4140/api/v1/ping"
COMPOSER_ENDPOINT = "http://0.0.0.0:4140/api/v1/composer"
COMPOSERS_ENDPOINT = "http://0.0.0.0:4140/api/v1/composers"

def ping():
    """ GET ping """
    r = requests.get(url = PING_ENDPOINT)

    result_text = r.text
    print("")
    print("result: " + result_text)

def ping_with_json():
    """ POST ping """
    name = get_input("name? ")
    now = datetime.datetime.now()
    now_str = now.strftime("%d/%m/%Y %H:%M:%S")
    json = {'name':name, 'timestamp': now_str}

    r = requests.post(url = PING_ENDPOINT, json = json)

    result_text = r.text
    print("")
    print("result: " + result_text)

def list_composers():
    """ GET all composers """
    r = requests.get(url = COMPOSERS_ENDPOINT)

    result_text = r.text
    print("")
    print("result: " + result_text)

def new_composer():
    """ POST new composer """
    name = get_input("name? ")
    era = get_input("era? ")
    json = {'name': name, 'era': era}

    r = requests.post(url = COMPOSER_ENDPOINT, json = json)

    print("result: " + r.text)

def get_composer_by_id():
    """ GET composer by id """
    id = get_input("id? ")
    r = requests.get(url = COMPOSER_ENDPOINT + "/" + id)

    result_text = r.text
    print("")
    print("result: " + result_text)

def update_composer():
    """ PUT update to composer """
    id = get_input("id? ")
    name = get_input("name? ")
    era = get_input("era? ")
    json = {'name':name, 'era':era}

    r = requests.put(url = COMPOSER_ENDPOINT + "/" + id, json = json)

    print("result: " + r.text)

def delete_composer():
    """ DELETE composer by id """
    id = get_input("id? ")
    r = requests.delete(url = COMPOSER_ENDPOINT + "/" + id)

    result_text = r.text
    print("")
    print("result: " + result_text)

# ----------------------------------
# user prompt loop

def go():
    """ main loop """
    done = False

    while not done:
        result = get_selection_from_menu()
        if result == '1':
            ping()
        elif result == '2':
            ping_with_json()
        elif result == '3':
            list_composers()
        elif result == '4':
            new_composer()
        elif result == '5':
            get_composer_by_id()
        elif result == '6':
            update_composer()
        elif result == '7':
            delete_composer()
        elif result.lower() == 'q':
            print("ok ... quitting")
            sys.exit(0)

def get_input(prompt):
    """ prompt user for input """
    print("")
    print(prompt)
    result = input()
    return result

def get_selection_from_menu():
    """ menu """
    result = None

    print("")
    print("1 - ping")
    print("2 - ping with json")
    print("3 - get composers")
    print("4 - new composer")
    print("5 - get composer by id")
    print("6 - update composer by id")
    print("7 - delete composer by id")
    print("q - quit")
    print("")
    print("enter choice:")
    result = input()

    return result

if __name__ == '__main__':
    go()
