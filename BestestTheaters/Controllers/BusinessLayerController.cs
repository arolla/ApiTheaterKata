using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace BestestTheaters.Controllers
{
    [Authorize]
    [ApiController]
    [Route("[controller]")]
    public class BusinessLayerController : ControllerBase
    {
        private static readonly string[] Summaries = new[]
        {
            "Miraculous", "Les As de la jungle 2", "Jeanne du Barry",
            "Anatomie d'une chute", "Tempête", "Passages", "Mon chat et moi",
            "Les Choses simples", "Le Bleu du caftan", "Mon crime"
        };

        private readonly ILogger<BusinessLayerController> _logger;

        public BusinessLayerController(ILogger<BusinessLayerController> logger)
        {
            _logger = logger;
        }

        [HttpGet(Name = "GetAllShows")]
        public IEnumerable<Show> Get(DateTime date)
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
            return Summaries[Random.Shared.Next(Summaries.Length)];
        }
    }
}