Feature: search

  Background:
    Given fill main filters

  Scenario: text in result of search is correct after main filters
    Then text on page after main filters matches with text in case


  Scenario: text in result of search is correct after additional filters
    When fill additional filters
    Then text on page after additional filters matches with text in case

  Scenario Outline: values in ad is correct after all filters
    When fill additional filters
    And click to first ad
    And switch to ad page
    And press hide hint
    Then values in ad matches with "<minSquare>","<maxPrice>"
    Examples:
      | minSquare | maxPrice |
      | 50        | 300000   |





