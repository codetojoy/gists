import unittest
from script import my_func
from script import MyClass 

class TestStringMethods(unittest.TestCase):

    def test_upper(self):
        self.assertEqual('foo'.upper(), 'FOO')

    def test_my_func(self):
        result = my_func(5000,150)
        self.assertEqual(result, 5150)

    def test_do_square(self):
        my_c = MyClass()
        result = my_c.do_square(25)
        self.assertEqual(result, 625)

if __name__ == '__main__':
    unittest.main()
