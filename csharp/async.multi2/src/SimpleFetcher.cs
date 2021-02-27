using System.Threading.Tasks;

namespace async.multi2
{
    public class SimpleFetcher : IFetcher
    {
        private readonly string whoAmI = "SimpleFetcher";

        public Task<int> Fetch()
        {
            Logger.Log($"{whoAmI} Fetch() begin");
            int maxCard = 5;
            var task = new Task<int>(() => maxCard);
            task.RunSynchronously();
            return task;
        }
    }
}
