using BestestTheater.WebApp.Models;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BestestTheater.WebApp.Pages
{
    public class BookModel : PageModel
    {
        private readonly ILogger<BookModel> _logger;
        private readonly ShowService showService;
        public string Message { get; private set; }

        public BookModel(ILogger<BookModel> logger, ShowService showService)
        {
            _logger = logger;
            this.showService = showService;
        }

        public void OnGet()
        {
            var session = HttpContext.Request.Query["sessionId"].First();
            var sessionId = int.Parse(session);
            var shows = showService.FetchShows();
            var showBooked = shows.FirstOrDefault(show => show.Id == sessionId);
            if (showBooked == null)
            {
                Message = "The session you are trying to book does not exist!";
                return;
            }
            Message = $"You booked the session - {showBooked.Title} - of - {showBooked.Date.ToString("yyyy-MM-dd")} -!";
            showService.Book(showBooked);
        }
    }
}