
Feature: Login Feature

  Scenario Outline: Successful login
    Given Users enters "<users>" credentials status code as "200"
    When user has this profile "<profile>"
    Then user has the following permissions "<permissions>"
    Examples:
      | users          | profile               | permissions                                                                                                                                                                                                                                                                                                                                       |
      | SUPER_ADMIN    | [SUPER_ADMINISTRATOR] | DeleteAccount,DeleteBroker,DeleteEntity,DeleteInstrument,DeleteInstrumentRating,DeleteOrder,DeleteResetRule,DeleteUser,ReadUser,SetUserDailyLimitAmount,UpsertAccount,UpsertBroker,UpsertCloseResetPeriod,UpsertEntity,UpsertInstrument,UpsertInstrumentRating,UpsertLockResetPeriod,UpsertOpenResetPeriod,UpsertOrder,UpsertResetRule,UpsertUser |
      | ADMIN          | [ADMINISTRATOR]       | DeleteAccount,DeleteBroker,DeleteEntity,DeleteInstrument,DeleteInstrumentRating,DeleteResetRule,DeleteUser,ReadUser,SetUserDailyLimitAmount,UpsertAccount,UpsertBroker,UpsertEntity,UpsertInstrument,UpsertInstrumentRating,UpsertResetRule,UpsertUser                                                                                            |
      | INVESTOR       | [INVESTOR]            | DeleteOrder,UpsertOrder                                                                                                                                                                                                                                                                                                                           |
      | INVESTOR_ADMIN | [INVESTOR_ADMIN]      | DeleteOrder,ReadUser,SetUserDailyLimitAmount,UpsertOrder                                                                                                                                                                                                                                                                                          |
