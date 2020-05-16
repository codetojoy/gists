import unittest
from unittest.mock import MagicMock
from unittest.mock import patch

from script import my_func
from script import MyClass 
from script import Foo 
from script import Bar 

class TestStringMethods(unittest.TestCase):

    def test_upper(self):
        self.assertEqual('foo'.upper(), 'FOO')

    def test_my_func(self):
        # test 
        result = my_func(5000,150)

        self.assertEqual(result, 5150)

    def test_myclass_do_square(self):
        my_c = MyClass()

        # test
        result = my_c.do_square(25)

        self.assertEqual(result, 625)

    @patch('os.path.isfile', return_value=False)
    def test_myclass_check_file(self, mock_isfile):
        file = 'bogus'
        my_c = MyClass()

        # test
        result = my_c.check_file(file)

        self.assertFalse(result)
        self.assertTrue(mock_isfile.called) 

    def test_foo_go(self):
        foo = Foo()
        bar = Bar()
        bar.go = MagicMock(return_value='mock value')

        # test
        result = foo.go(bar)

        self.assertEqual(result, 'mock value')

if __name__ == '__main__':
    unittest.main()
