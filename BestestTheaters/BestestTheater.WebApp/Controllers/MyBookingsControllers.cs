using Microsoft.AspNetCore.Mvc;

namespace BestestTheater.WebApp.Controllers
{
    public class MyBookingsControllers : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
