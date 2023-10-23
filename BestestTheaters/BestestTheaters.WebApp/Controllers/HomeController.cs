using BestestTheaters.WebApp.Dto;
using BestestTheaters.WebApp.Models;
using BestestTheaters.WebApp.Services;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace BestestTheaters.WebApp.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly IBusinessLayerFacade businessLayerFacade;

        public HomeController(IBusinessLayerFacade businessLayerFacade, ILogger<HomeController> logger)
        {
            _logger = logger;
            this.businessLayerFacade = businessLayerFacade;
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
            IEnumerable<ShowDto> shows = businessLayerFacade.FetchShowsFromApi(); // Remplacez par la logique de récupération des données depuis l'API
            var showViewModels = shows.Select(s => new ShowViewModel
            {
                Date = s.Date,
                Title = s.Title,
            });

            return View("Shows",showViewModels);
        }

       
    }
}