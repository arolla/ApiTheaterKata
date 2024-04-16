using System.Text.Json.Serialization;

namespace BestestTheaters.Api.Dto
{
    public class Show
    {
        [JsonPropertyName("Date")]
        public DateTime Date { get; set; }
        [JsonPropertyName("Title")]
        public string Title { get; set; }
        [JsonPropertyName("Id")]
        public int Id { get; set; }
    }
}