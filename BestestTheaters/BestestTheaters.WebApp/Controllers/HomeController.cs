using BestestTheaters.WebApp.Dto;
using BestestTheaters.WebApp.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace BestestTheaters.WebApp.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }

        public IActionResult GetAllShows()
        {
            IEnumerable<ShowDto> shows = FetchShowsFromApi(); // Remplacez par la logique de récupération des données depuis l'API
            var showViewModels = shows.Select(s => new ShowViewModel
            {
                Date = s.Date,
                Title = s.Title,
                // Assurez-vous de mapper d'autres propriétés si nécessaire
            });

            return View("Shows",showViewModels);
        }

        private IEnumerable<ShowDto> FetchShowsFromApi()
        {            
            return Enumerable.Range(1, 5).Select(index => new ShowDto
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