namespace BestestTheater.WebApp.Models
{
    public class ShowService
    {
        private static List<BkngData_3> BookedShows { get; set; } = new();

        public IEnumerable<Show> FetchShows()
        {
            var TOMORROW = DateTime.Now.AddDays(1);
            var shows = GetShows(TOMORROW);
            return shows;
        }

        private static List<Show> GetShows(DateTime date)
        {
            var shows = new List<Show>
            {
                new() { Id = 1, Date = date, Title = "Miraculous" },
                new() { Id = 2, Date = date, Title = "Les As de la jungle 2" },
                new() { Id = 3, Date = date, Title = "Anatomie d'une chute" },
                new() { Id = 4, Date = date, Title = "Tempête" },
                new() { Id = 5, Date = date, Title = "Passages" },
                new() { Id = 6, Date = date, Title = "Mon chat et moi" },
                new() { Id = 7, Date = date, Title = "Les Choses simples" },
                new() { Id = 8, Date = date, Title = "Le Bleu du caftan" },
                new() { Id = 9, Date = date, Title = "Mon crime" }
            };
            return shows;
        }

        public List<BkngData_3> GetMyBookings()
        {
            return BookedShows;
        }

        public void Book(Show showBooked)
        {
            var seatsAsStrings = GetSeats();
            var details = seatsAsStrings.Aggregate((prev, next) => prev + ", " + next);
            BookedShows.Add(new BkngData_3 { Title = showBooked.Title, Date = showBooked.Date, Details = details });
        }

        private static List<string> GetSeats()
        {
            var randomSeatSeed = new Random().Next(0, 100);
            var randSeatCount = new Random().Next(1, 5);
            var seatsAsStrings = new List<string>();
            for (var j = 0; j < randSeatCount; j++)
            {
                int randomizedNumber = new Random().Next(randomSeatSeed, randomSeatSeed + 100);

                seatsAsStrings.Add($"Seats {randomizedNumber.ToString("D2")}");
            }

            return seatsAsStrings;
        }
    }
}
