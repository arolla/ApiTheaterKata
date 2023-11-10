using BestestTheaters.Api.Dto;

namespace BestestTheaters.Api.Services
{
    public class BusinessLayerFacade : IBusinessLayerFacade
    {
        public IEnumerable<Show> FetchShows()
        {
            return Enumerable.Range(1, 5).Select(index => new Show
            {
                Date = DateOnly.FromDateTime(DateTime.Now.AddDays(index)),
                Title = GetRundomTitle()
            })
            .ToArray();
        }

        private static string GetRundomTitle()
        {
            string[] Summaries = new[]
            {
                "Miraculous", "Les As de la jungle 2", "Jeanne du Barry",
                "Anatomie d'une chute", "Tempête", "Passages", "Mon chat et moi",
                "Les Choses simples", "Le Bleu du caftan", "Mon crime"
            };
            return Summaries[Random.Shared.Next(Summaries.Length)];
        }
    }
}
