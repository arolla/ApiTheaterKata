using BestestTheaters.Api.Dto;

namespace BestestTheaters.Api.Services
{
    public interface IBusinessLayerFacade
    {
        IEnumerable<Show> FetchShows();
    }
}