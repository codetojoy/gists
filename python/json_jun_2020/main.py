import json
from collections import namedtuple

f = open("data.json", "r")
json_str = f.read()
json_dict = json.loads(json_str)

num_cards = json_dict["num_cards"]
print("TRACER num_cards: " + str(num_cards))

players = json_dict["players"]
for player in players:
    print("TRACER player name: " + player["name"])

print("Ready.")
