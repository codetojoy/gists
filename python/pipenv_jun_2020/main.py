
from bar.person import build_person
from xyz.location import build_location

Person = build_person()
person = Person(name="Mozart", date_of_birth="27-JAN-1756")
print(str(person)) 

print("")

Location = build_location()
location = Location(country="Austria", city="Salzburg")
print(str(location)) 

print("Ready.")
