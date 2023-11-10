using BestestTheaters.Api.Dto;
using NFluent;
using System.Net;
using System.Text;
using System.Text.Json;

namespace BestestTheaters.Api.Tests
{
    [TestClass]
    public class ShowsApiTest
    {
        [TestMethod]
        public async Task ShouldReturnAllShows()
        {
            const string url = @"http://localhost:19792/Shows";
            using HttpResponseMessage response = await new HttpClient().GetAsync(url);
            response.EnsureSuccessStatusCode();
            string responseBody = await response.Content.ReadAsStringAsync();
            var shows = JsonSerializer.Deserialize<Show[]>(responseBody);
            Check.That(shows).IsNotNull();
            Check.That(shows.All(show => !string.IsNullOrEmpty(show.Title))).IsTrue();
        }

    }
}