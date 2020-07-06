
import requests
import sys

# ----------------------------------
# REST api calls

API_ENDPOINT = "http://0.0.0.0:4996/booksApi"

def list_books():
    """ GET all books """
    r = requests.get(url = API_ENDPOINT)

    result_text = r.text
    print("")
    print("result: " + result_text)

def new_book():
    """ POST new book """
    title = get_input("title? ")
    author = get_input("author? ")
    genre = get_input("genre? ")
    params = {'title':title, 'author':author, 'genre': genre}

    r = requests.post(url = API_ENDPOINT, params = params)

    print("result: " + r.text)

def get_book():
    """ GET book by id """
    id = get_input("id? ")
    r = requests.get(url = API_ENDPOINT + "/" + id)

    result_text = r.text
    print("")
    print("result: " + result_text)

def update_book():
    """ PUT update to book """
    id = get_input("id? ")
    title = get_input("title? ")
    author = get_input("author? ")
    genre = get_input("genre? ")
    params = {'title':title, 'author':author, 'genre': genre}

    r = requests.put(url = API_ENDPOINT + "/" + id, params = params)

    print("result: " + r.text)

def delete_book():
    """ DELETE book by id """
    id = get_input("id? ")
    r = requests.delete(url = API_ENDPOINT + "/" + id)

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
            list_books()
        elif result == '2':
            new_book()
        elif result == '3':
            get_book()
        elif result == '4':
            update_book()
        elif result == '5':
            delete_book()
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
    print("1 - get books")
    print("2 - add book")
    print("3 - get book by id")
    print("4 - update book by id")
    print("5 - delete book by id")
    print("q - quit")
    print("enter your pick:")
    result = input()

    return result

if __name__ == '__main__':
    go()
