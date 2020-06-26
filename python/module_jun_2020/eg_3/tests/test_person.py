
from bar.person import build_person

def test_build_person_simple():

    # test
    Person = build_person()
    person = Person("Mozart", "27-JAN-1756") 
    
    assert person.name == "Mozart"
    assert person.date_of_birth == "27-JAN-1756"
