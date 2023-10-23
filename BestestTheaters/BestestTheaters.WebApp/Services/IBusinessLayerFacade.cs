using BestestTheaters.WebApp.Dto;

namespace BestestTheaters.WebApp.Services
{
    public interface IBusinessLayerFacade
    {
        IEnumerable<ShowDto> FetchShowsFromApi();
    }
}