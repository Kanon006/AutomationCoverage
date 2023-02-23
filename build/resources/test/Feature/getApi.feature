Feature:
  @Smoke
  Scenario Outline: get api check of pages
    Given user has the base api
    When user call the'<pramsName>' and '<pramsValue>'
    Then it will return valid data
    Examples:
      |pramsName|pramsValue|
      |?page    | 2        |