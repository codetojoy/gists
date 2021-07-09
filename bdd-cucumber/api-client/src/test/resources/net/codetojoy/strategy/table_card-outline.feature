Feature: illustrate tables

  boilerplate here

  Scenario Outline: Cucumber Data Table
    Given prizeCard: <prizeCard> maxCard: <maxCard> cards: <cards> mode: <mode>
    When I select card
    Then card selection should be <expected>

    Examples:
    | prizeCard | maxCard | cards         | mode      | expected |
    | 10        | 22      | "2,4,6"       | "max"     | 6        |
    | 10        | 22      | "2,4,6"       | "min"     | 2        |
    | 10        | 22      | "4,6,2"       | "next"    | 4        |
    | 10        | 22      | "4,3,8,14,20" | "nearest" | 8        |
