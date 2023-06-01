Feature: Admin

  Scenario: Admin workflow
    Given the user is on the home page
    And the user is an admin
    When the admin enters "admin@email.com" in the "Email" field
    When the admin enters "admin" in the "Password" field
    Then the admin clicks on the login button
    Then the admin see the orders
    Then the admin change the status of the pickup to "Partner"
    Then the admin logout
