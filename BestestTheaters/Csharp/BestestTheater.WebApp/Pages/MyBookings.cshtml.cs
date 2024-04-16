using BestestTheater.WebApp.Models;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BestestTheater.WebApp.Pages
{
    public class MyBookingsModel : PageModel
    {
        private readonly ShowService _showService;

        public MyBookingsModel(ShowService showService)
        {
            _showService = showService;
        }
        public IEnumerable<BkngData_3> BkngData { get; private set; }
     
        public void OnGet()
        {
            BkngData = _showService.GetMyBookings();
        }
    }
}
