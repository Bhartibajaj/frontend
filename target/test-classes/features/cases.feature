Feature: UI validation
  I want to use this template for my feature file

 @tag1
  Scenario: show records button task1
    Given user is on homepage
    When user click on show record dropdown
   And select show100 records
   Then selected records should visible
    
     @tag1
  Scenario: show filtered records button task2
    Given user is on homepage
    When user click on filter button
    And Filter records by "MarketCap= $1B to $10B" and "Price: $101 - $1000 "
    Then records displayed on page are correct as per the filter applied
    
