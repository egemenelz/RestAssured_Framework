Feature: Login Feature

  Scenario Outline: Successful login
    Given Users enters "<users>" credentials status code as "200"
    When Users expected profile matches with the actual "<profile>"
    Then user has the following permissions "<permissions>"
    Examples:
      | users          | profile               | permissions                                                                                                                                                                                                                                                                                                                                       |
      | SUPER_ADMIN    | [SUPER_ADMINISTRATOR] | DeleteAccount,DeleteBroker,DeleteEntity,DeleteInstrument,DeleteInstrumentRating,DeleteOrder,DeleteResetRule,DeleteUser,ReadUser,SetUserDailyLimitAmount,UpsertAccount,UpsertBroker,UpsertCloseResetPeriod,UpsertEntity,UpsertInstrument,UpsertInstrumentRating,UpsertLockResetPeriod,UpsertOpenResetPeriod,UpsertOrder,UpsertResetRule,UpsertUser |
      | ADMIN          | [ADMINISTRATOR]       | DeleteAccount,DeleteBroker,DeleteEntity,DeleteInstrument,DeleteInstrumentRating,DeleteResetRule,DeleteUser,ReadUser,SetUserDailyLimitAmount,UpsertAccount,UpsertBroker,UpsertEntity,UpsertInstrument,UpsertInstrumentRating,UpsertResetRule,UpsertUser                                                                                            |
      | INVESTOR       | [INVESTOR]            | DeleteOrder,UpsertOrder                                                                                                                                                                                                                                                                                                                           |
      | INVESTOR_ADMIN | [INVESTOR_ADMIN]      | DeleteOrder,ReadUser,SetUserDailyLimitAmount,UpsertOrder                                                                                                                                                                                                                                                                                          |

  @login
  Scenario Outline: Unsuccessful login
    Given Users enters "<users>" and "<password>" credentials
    When User send a request to "/EVENT-LOGIN-AUTH" route
    Then The system response the request with "INCORRECT_CREDENTIALS" message
    Examples:
      | users                        | password      |
      | JohnDoe                      | WrongPassword |
      | anna.investor@test.com       | WrongPassword |
      | anna.investor.admin@test.com | WrongPassword |
      | anna.administrator@test.com  | WrongPassword |
