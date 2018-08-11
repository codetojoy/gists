
### Intro

The old CSV schema isn't very flexible, and could use updating.

### My View

* CSV sucks but it is better than all the other formats for this kind of data.
* IMO, the killer app is the GitHub CSV preview, with the filter box. It's free and useful.

### Schema

old:

|Old          |     |          |         |       |         |       |        |           |
|-------------|-----|----------|---------|-------|---------|-------|--------|-----------|
|Date|#|Venue|Cat Herder|Speaker 1|Topic 1|Speaker 2|Topic 2|Sponsors|A/V|

new:

|New | |     |          |    |         |       |         |       |        |   |            |
|----|-|-----|----------|----|---------|-------|---------|-------|--------|---|------------|
|Date|#|Venue|Cat Herder|Type|Speaker 1|Topic 1|Speaker 2|Topic 2|Sponsors|A/V|Type Context|

* if `Type` is blank: it's a normal meetup and `Type Context` is blank
* if `Type` is `L`: it's lighting and `Type Context` has speakers
* if `Type` is `P`: it's a panel and `Type Context` has panelists

### Other notes

* example is [here](MeetUps_v2.csv) 
* for brevity, there are now keys for [venues](https://github.com/codetojoy/gists/blob/master/peidevs_data/Venue_v2.csv) and [meeting types](https://github.com/codetojoy/gists/blob/master/peidevs_data/Type_v2.csv)

