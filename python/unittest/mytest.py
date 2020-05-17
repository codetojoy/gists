import io
import unittest
from unittest.mock import MagicMock
from unittest.mock import patch, mock_open

from script import my_func
from script import MyClass, Foo, Bar 

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
        mock_isfile.assert_called_with(file)

    # https://stackoverflow.com/a/24325868/12704
    def test_myclass_read_matching_lines(self): 
        path = '/bogus'
        regex = r'foobar'
        my_c = MyClass()

        fake_file = io.StringIO('my foobar\nquick brown fox\nfoobar 3\n')
        with patch('builtins.open', return_value=fake_file, create=True):
            # test
            result = my_c.read_matching_lines(path, regex)

        self.assertEqual(2, result)

    def test_foo_go(self):
        foo = Foo()
        bar = Bar()
        bar.go = MagicMock(return_value='mock value')

        # test
        result = foo.go(bar)

        self.assertEqual(result, 'mock value')

    @patch('shutil.copyfile')
    def test_myclass_copy_file(self, mock_copyfile):
        a = '/some/path/to/somefile'
        b = '/some/path/to/somefile'
        my_c = MyClass()

        with mock_copyfile:
            # test
            result = my_c.copy_file(a, b)

        mock_copyfile.assert_called_with(a, b)

if __name__ == '__main__':
    unittest.main()
