using BestestTheaters.Api.Controllers;
using BestestTheaters.Api.Services;
using Microsoft.Extensions.Logging;
using NFluent;

namespace BestestTheaters.Api.Tests
{
    [TestClass]
    public class ShowsControllerIntegrationTest
    {
        [TestMethod]
        public void ShouldReturnAllShows()
        {
            ILogger<ShowsController> logger = NSubstitute.Substitute.For<ILogger<ShowsController>>();
            IBusinessLayerFacade businessFacade = new BusinessLayerFacade();
            var showsController = new ShowsController(logger, businessFacade);
            var shows = showsController.Get();
            Check.That(shows).IsNotNull();
        }
    }
}