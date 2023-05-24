Feature: Register a pickup
  Scenario: Register a pickup with random data
    Given I have a valid pickup registration endpoint at "localhost:8080/pickup/register"
    When I send a POST request with the following payload:
      | name        | email                | phone         | password    | address        | status   |
      | RandomName  | random@example.com   | 1234567890    | randomPass   | RandomAddress  | Pending  |
    Then the response status code should be 200