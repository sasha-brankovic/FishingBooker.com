Feature: Fishing Booker

Scenario Outline: Booking Single Fisherman Special
  Given I am on page Pike Predator Tour
  When I get available termin for Single Fisherman Special
  And I start with enter my details necessary for booking "<firstName>" "<lastname>" "<email>" "<phone>" "<greetingCaptain>"
  And I enter card info for payment "<cardNumber>" "<cardExpiration>" "<cardSecurityCode>" "<cardHolderName>" "<postalCode>"
  Then I get my booking Id


  Examples:
    | firstName | lastname | email                     | phone      | greetingCaptain         | cardNumber       | cardExpiration | cardSecurityCode | cardHolderName  | postalCode |
    | Dmitar    | Acimovic | dmitar.acimovic@email.com | 2026532487 | Oh Captain! My Captain! | 4111111111111111 | 0525           | 000              | DMITAR ACIMOVIC | 10000      |
