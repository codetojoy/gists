def s = '''
{
    "userInformation": {
        "Name": "John",
        "Location": "India"
    },
    "details": [
        {
            "fruit": "Apple  ",
            "color": " Red",
            "city": "New Delhi",
            "luckyNumber": 10
        },
        {
            "fruit": "Banana ",
            "color": "yellow ",
            "city": "Goa",
            "luckyNumber": 12
         }
    ]
 }
'''

def json = new groovy.json.JsonSlurper().parseText(s)
def f = 'Apple'
def c = 'Red'

def trimCompare = { a, b ->
    a.trim() == b.trim()
}

def cityName = json.details
                   .find { trimCompare(it.fruit,f) && trimCompare(it.color,c) }
                  ?.city
assert 'New Delhi' == cityName
