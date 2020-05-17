
import os
import re
import shutil

def my_func(x, y):
    return x+y

class MyClass:
    def do_square(self, x):
        return x*x

    def check_file(self, file):
        return os.path.isfile(file) 

    def copy_file(self, a, b):
        shutil.copyfile(a, b)

    def read_matching_lines(self, path, regex):
        print('TRACER cp 1')
        count = 0
        with open(path, 'r') as my_file:
            print('TRACER cp 2')
            for line in my_file:
                print('TRACER cp 3')
                print('TRACER line: {}'.format(line))
                found = re.findall(regex, line)
                if found:
                    count += 1
        return count

class Foo:
    def go(self, bar):
        return bar.go()

class Bar:
    def go(self, s):
        return s
