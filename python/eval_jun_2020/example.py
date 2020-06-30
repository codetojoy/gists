
import sys
import my_module

if __name__ == "__main__":
    s = sys.argv[1]
    f = getattr(my_module, s)
    f() 
    print("Ready")
