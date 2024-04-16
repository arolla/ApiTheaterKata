namespace BestestTheater.WebApp.Models
{
    public class ShowService
    {
        public IEnumerable<Show> FetchShows()
        {
            var TOMORROW = DateTime.Now.AddDays(1);
            var shows = new List<Show>
            {
                new Show{ Id =1, Date = TOMORROW, Title = "Miraculous"},
                new Show{ Id =2, Date = TOMORROW, Title = "Les As de la jungle 2"},
                new Show{ Id =3, Date = TOMORROW, Title = "Anatomie d'une chute"},
                new Show{ Id =4, Date = TOMORROW, Title = "Tempête"},
                new Show{ Id =5, Date = TOMORROW, Title = "Passages"},
                new Show{ Id =6, Date = TOMORROW, Title = "Mon chat et moi"},
                new Show{ Id =7, Date = TOMORROW, Title = "Les Choses simples"},
                new Show{ Id =8, Date = TOMORROW, Title = "Le Bleu du caftan"},
                new Show{ Id =9, Date = TOMORROW, Title = "Mon crime"}
            };
            return shows;
        }

        public List<BkngData_3> GetMyBookings()
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
