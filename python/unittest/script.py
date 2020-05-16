
import os

def my_func(x, y):
    return x+y

class MyClass:
    def do_square(self, x):
        return x*x

    def check_file(self, file):
        return os.path.isfile(file) 

class Foo:
    def go(self, bar):
        return bar.go()

class Bar:
    def go(self, s):
        return s
