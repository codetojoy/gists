import sys
import json
from collections import namedtuple

def main():
    try: 
        f = open("data.json", "r")
        json_str = f.read()
        json_dict = json.loads(json_str)

        num_cards = json_dict["num_cards"]
        print("TRACER num_cards: " + str(num_cards))

        players = json_dict["players"]
        for player in players:
            print("TRACER player name: " + player["name"])

        print("Ready.")
    except: 
        e = sys.exc_info()[0]
        print("TRACER illegal json: " + str(e))
        sys.exit(-1)

if __name__ == "__main__":
    # execute only if run as a script
    main()
