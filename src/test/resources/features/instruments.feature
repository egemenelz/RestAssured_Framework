@inst
Feature: Instruments Feature

  Scenario: GET All The Instruments
    Given "SUPER_ADMIN" user send a request to endpoint with "/all-instruments" status code as "200"
    And Message type on payload is "QUERY_UPDATE"
    When User views the row on the payload
    Then ROWS_COUNT must be equal to ROW

