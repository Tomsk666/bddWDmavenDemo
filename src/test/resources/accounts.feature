@run
Feature: Accounts
  deals with customer accounts/registration

  Scenario: Registration Positive
    When i register as a new user with 'user08@edgewords.co.uk' and '!password1234567890Q!'
    Then i get a welcome message

  Scenario: Registration Negative
    When i try to register with an existing user 'user05@edgewords.co.uk' and '!password1234567890Q!'
    Then i get an error message that the user is already registered

  Scenario: Login Positive
    When i login with valid credentials 'user05@edgewords.co.uk' and '!password1234567890Q!'
    Then i get access to My Account