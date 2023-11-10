using System.Text.Json.Serialization;

namespace BestestTheaters.Api.Dto
{
    public class Show
    {
        [JsonPropertyName("Date")]
        public DateOnly Date { get; set; }
        [JsonPropertyName("Title")] 
        public string Title { get; set; }
    }
}