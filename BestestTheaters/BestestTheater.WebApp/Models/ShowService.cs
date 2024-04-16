namespace BestestTheater.WebApp.Models
{
    public class ShowService
    {
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
            var data = new List<BkngData_3>();

            for (var i = 0; i < 10; i++)
            {
                var seatSeed = new Random().Next(0, 100);
                var randomSeatSeed = seatSeed;

                var randSeatCount = new Random().Next(1, 5);

                var seatsAsStrings = new List<string>();
                for (var j = 0; j < randSeatCount; j++)
                {
                    int randomizedNumber = new Random().Next(randomSeatSeed, randomSeatSeed + 100);

                    seatsAsStrings.Add($"Seats {randomizedNumber.ToString("D2")}");
                }

                var details = seatsAsStrings.Aggregate((prev, next) => prev + ", " + next);

                var dateTime = new DateTime(2024, 1, 2, 13, 16, 0, DateTimeKind.Utc);
                var sessions = GetShows(dateTime);
                var sessionIndex = new Random().Next(0, sessions.Count);
                var oneRow = new BkngData_3()
                {
                    Title = sessions[sessionIndex].Title,
                    Date = dateTime,
                    Details = details
                };

                data.Add(oneRow);
            }

            return data;
        }
    }
}
