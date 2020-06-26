
from location import build_location

def test_build_location_simple():

    # test
    Location = build_location()
    location = Location("Canada", "Charlottetown")

    assert location.country == "Canada"
    assert location.city == "Charlottetown"

