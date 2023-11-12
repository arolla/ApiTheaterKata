Feature: ShowsAcceptanceTest

Return planned shows

@tag1
Scenario: Select first show of planned shows
	Given a planned shows
	When I select the first show
	Then the show detail should be enabled
