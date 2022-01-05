Feature: Git Hub sign up
  As a user
  I want to test sign up functionality of the site https://github.com
  So that I can be sure that this site works correctly

  Scenario Outline: Check that error message is displayed upon registration with invalid email pattern
    Given User opens '<signUpPage>' page
    When User enters email '<invalidEmail>' into email field
    Then User checks error message visibility
    And User checks that 'Continue' button is disabled


    Examples:
      | signUpPage                | invalidEmail |
      | https://github.com/signup | invalid123   |


  Scenario Outline: Check that message under password field indicates a completeness of password requirements
    Given User opens '<signUpPage>' page
    And User enters email '<validEmail>' into email field
    And User clicks 'Continue' button
    When User enters '<invalidPassword1>' into password field
    Then User checks that the warning message is '<redMessage>'
    And User checks that warning message is marked '<redColorHexCode>'
    When User enters '<invalidPassword2>' into password field
    Then User checks that the warning message is '<yellowMessage>'
    And User checks that warning message is marked '<yellowColorHexCode>'
    When User enters '<validPassword>' into password field
    Then User checks that the warning message is '<greenMessage>'
    And User checks that warning message is marked '<greenColorHexCode>'
    And User checks that 'Continue' button is enabled


    Examples:
      | signUpPage                | validEmail       | invalidPassword1 | redMessage            | redColorHexCode | invalidPassword2 | yellowColorHexCode | yellowMessage                                | validPassword | greenMessage       | greenColorHexCode |
      | https://github.com/signup | semina@gmail.com | AAA              | Password is too short | #ed4e50         | AAAaaaaaa        | #ffd33d            | Password needs a number and lowercase letter | AAAaaaaaa12   | Password is strong | #2da44e           |


  Scenario Outline: Check that error message is displayed upon registration with previously registered email
    Given User opens '<signUpPage>' page
    When User enters email '<previouslyRegisteredEmail>' into email field
    Then User checks that the error message is '<errorMessage>'
    And User checks that 'Continue' button is disabled


    Examples:
      | signUpPage                | previouslyRegisteredEmail | errorMessage                      |
      | https://github.com/signup | seminasv@gmail.com        | Email is invalid or already taken |