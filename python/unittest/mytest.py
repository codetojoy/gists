import unittest
from unittest.mock import MagicMock
from script import my_func
from script import MyClass 
from script import Foo 
from script import Bar 

class TestStringMethods(unittest.TestCase):

    def test_upper(self):
        self.assertEqual('foo'.upper(), 'FOO')

    def test_my_func(self):
        result = my_func(5000,150)
        self.assertEqual(result, 5150)

    def test_myclass_do_square(self):
        my_c = MyClass()
        result = my_c.do_square(25)
        self.assertEqual(result, 625)

    def test_foo_go(self):
        foo = Foo()
        bar = Bar()
        bar.go = MagicMock(return_value='mock value')
        result = foo.go(bar)
        self.assertEqual(result, 'mock value')

if __name__ == '__main__':
    unittest.main()
