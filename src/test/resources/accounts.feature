@run
Feature: Accounts
  deals with customer accounts/registration

  Scenario: Registration
    When i register as a new user with 'user05@edgewords.co.uk' and '!password1234567890Q!'
    Then i get a welcome message
