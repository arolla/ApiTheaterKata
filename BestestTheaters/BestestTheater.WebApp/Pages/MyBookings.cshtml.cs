using System.Collections;
using BestestTheater.WebApp.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BestestTheater.WebApp.Pages
{
    public class MyBookingsModel : PageModel
    {
        public IEnumerable<BkngData_3> BkngData { get; private set; }
     
        public void OnGet()
        {
            var data = GetMyBookings();
            BkngData = data;
        }

        private static List<BkngData_3> GetMyBookings()
        {
            var data = new List<BkngData_3>();

            for (var i = 0; i < 10; i++)
            {
                var randomSeatSeed = new Random().Next(0, 100);

                var randSeatCount = new Random().Next(1, 5);

                var seatsAsStrings = new List<string>();
                for (var j = 0; j < randSeatCount; j++)
                {
                    int randomizedNumber = new Random().Next(randomSeatSeed, randomSeatSeed + 100);

                    seatsAsStrings.Add($"Seats {randomizedNumber.ToString("D2")}");
                }

                var details = seatsAsStrings.Aggregate((prev, next) => prev + ", " + next);

                var oneRow = new BkngData_3()
                {
                    Title = $"Title{new Random().Next(0, 100).ToString("D2")}",
                    Date = new DateTime(2024, 1, 2, 13, 16, 0, DateTimeKind.Utc),
                    Details = details
                };

                data.Add(oneRow);
            }

            return data;
        }
    }
}
