@run
Feature: basket
  #shopping basket features

  Scenario: add to basket
  When i add an item to the basket
  Then i can view the item in my basket