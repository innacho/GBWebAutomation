Feature: JIRA authorisation test
  Positive test and negative tests

  Scenario: Positive auth test
    Given on login page
    When password is "inna1234"
    Then auth result is "INNA"

  Scenario Outline: Negative auth test
    Given on login page
    When password is "<password>"
    Then false auth result is "<answer>"

    Examples:
|     password    |       answer       |
|     inna1238    |   resetpassword    |
|     inna1239    |   resetpassword    |