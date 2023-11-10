using BestestTheaters.Api.Dto;
using BestestTheaters.Api.Services;
using Microsoft.AspNetCore.Mvc;

namespace BestestTheaters.Api.Controllers
{
    [ApiController]
    [Route("Shows")]
    public class ShowsController : ControllerBase
    {   
        private readonly ILogger<ShowsController> _logger;
        private readonly IBusinessLayerFacade businessFacade;

        public ShowsController(ILogger<ShowsController> logger, IBusinessLayerFacade businessFacade)
        {
            _logger = logger;
            this.businessFacade = businessFacade;
        }

        [HttpGet]
        public IEnumerable<Show> Get()
        {
            return this.businessFacade.FetchShows();
        }

    }
}