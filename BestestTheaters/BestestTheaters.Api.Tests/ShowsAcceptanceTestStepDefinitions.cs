using BestestTheaters.Api.Dto;
using NFluent;
using System.Text.Json;
using TechTalk.SpecFlow;

namespace BestestTheaters.Api.Tests
{
    [Binding]
    public class ShowsAcceptanceTestStepDefinitions
    {
        private readonly ShowContext showContext;

        public ShowsAcceptanceTestStepDefinitions(ShowContext showContext)
        {
            this.showContext = showContext;
        }

        [Given(@"a planned shows")]
        public async Task GivenAPlannedShows()
        {
            const string url = @"http://localhost:19792/Shows";
            using HttpResponseMessage response = await new HttpClient().GetAsync(url);
            response.EnsureSuccessStatusCode();
            string responseBody = await response.Content.ReadAsStringAsync();
            Show[]? shows = JsonSerializer.Deserialize<Show[]>(responseBody);
            showContext.PlannedShows = shows;
        }

        [When(@"I select the first show")]
        public async Task WhenISelectTheFirstShow()
        {
            string url = @"http://localhost:19792/Shows/" + showContext.PlannedShows.First().Id;
            using HttpResponseMessage response = await new HttpClient().GetAsync(url);
            response.EnsureSuccessStatusCode();
            string responseBody = await response.Content.ReadAsStringAsync();
            Show show = JsonSerializer.Deserialize<Show>(responseBody);
            showContext.FirstShow = show;
        }

        [Then(@"the show detail should be enabled")]
        public void ThenTheShowDetailShouldBeEnabled()
        {
            Check.That(showContext.FirstShow).HasFieldsWithSameValues(showContext.PlannedShows.First());
        }

        public class ShowContext
        {
            public Show[]? PlannedShows { get; internal set; }
            public Show? FirstShow { get; internal set; }
        }
    }
}
