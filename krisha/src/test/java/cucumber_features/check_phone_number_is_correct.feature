Feature: Check correct result of search

#  Background:
#    Given open main page
#    When fill main filters


  Scenario: check phone number
    When click to link new Buildings
    Then check that phone number matches with regex
